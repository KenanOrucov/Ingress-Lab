package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.GuideEntity;
import com.example.Ingress_lab.dao.entity.TourEntity;
import com.example.Ingress_lab.dao.repository.TourRepository;
import com.example.Ingress_lab.exception.GuideBusyException;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.model.request.TourRequest;
import com.example.Ingress_lab.model.response.TourResponse;
import com.example.Ingress_lab.service.abstraction.GuideService;
import com.example.Ingress_lab.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Ingress_lab.mapper.TourMapper.toTourEntity;
import static com.example.Ingress_lab.mapper.TourMapper.toTourResponse;
import static com.example.Ingress_lab.mapper.TourMapper.toTourResponses;
import static com.example.Ingress_lab.mapper.TourMapper.updateTourEntity;
import static com.example.Ingress_lab.model.enums.ExceptionConstants.GUIDE_BUSY;
import static com.example.Ingress_lab.model.enums.ExceptionConstants.TOUR_NOT_FOUND;
import static com.example.Ingress_lab.model.enums.GuideStatus.BUSY;
import static com.example.Ingress_lab.model.enums.EntityStatus.ACTIVE;
import static com.example.Ingress_lab.model.enums.EntityStatus.INACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceHandler implements TourService {
    private final TourRepository tourRepository;
    private final GuideService guideService;
    @Override
    public List<TourResponse> getAllTours() {
        return toTourResponses(tourRepository.findAllByTourStatus(ACTIVE));
    }

    @Cacheable("tours")
    @Override
    public TourResponse getTourById(Long id) {
        var tour = fetchTourIfExist(id);
        return toTourResponse(tour);
    }

    @Override
    public List<TourResponse> getToursByTravelerId(Long travelerId) {
        var tours = tourRepository.findToursByTravelerIdAndStatus(travelerId, ACTIVE.name());
        return toTourResponses(tours);
    }

    @Override
    @Transactional
    public void createTour(TourRequest request) {
        var tour = toTourEntity(request);
        setGuidesToTour(request, tour);
        tourRepository.save(tour);
    }

    @Override
    @Transactional
    public void updateTour(Long id, TourRequest request) {
        var tour = fetchTourIfExist(id);
        updateTourEntity(tour, request);

        setGuidesToTour(request, tour);

        tourRepository.save(tour);
    }

    @Override
    @Transactional
    public void deleteTour(Long id) {
        var tour = fetchTourIfExist(id);
        tour.setTourStatus(INACTIVE);
        updateCache(id);
        tourRepository.save(tour);
    }

    @CacheEvict(allEntries = true, value = "tours")
    @Override
    public void clearCache() {

    }

    @CachePut(value = "tours")
    public void updateCache(Long id){
        getTourById(id);
    }

    public TourEntity fetchTourIfExist(Long id) {
        var tour = tourRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(TOUR_NOT_FOUND.getCode(), TOUR_NOT_FOUND.getMessage()));

        if (tour.getTourStatus() == INACTIVE) {
            throw new NotFoundException(TOUR_NOT_FOUND.getCode(), TOUR_NOT_FOUND.getMessage());
        }
        return tour;
    }

    private void setGuidesToTour(TourRequest request, TourEntity tour) {
        if (request.getGuideIds() != null){
            log.info("ActionLog.updateTour.guideIds: {}", request.getGuideIds());

            var guides = request
                    .getGuideIds()
                    .stream()
                    .map(guideService::fetchGuideIfExist)
                    .collect(Collectors.toSet());

            log.info("ActionLog.updateTour.guides is fetched");
            var availableGuides = guides.stream()
                    .filter(guide -> isGuideAvailable(guide, tour.getStartDate()))
                    .collect(Collectors.toSet());

            log.info("ActionLog.updateTour.available guides is selected");
            availableGuides
                    .forEach(guide -> guideService.changeGuideStatus(guide.getId(), BUSY));

            tour.setGuides(availableGuides);
        }
    }

    private boolean isGuideAvailable(GuideEntity guide, LocalDate tourStartDate) {
        List<TourEntity> existingTours = tourRepository.findToursByGuideId(guide.getId());
        boolean hasConflict = existingTours.stream()
                .anyMatch(tour -> !tourStartDate.isBefore(tour.getStartDate()) && !tourStartDate.isAfter(tour.getEndDate()));

        if (hasConflict) {
            log.error("ActionLog.updateTour.guides is busy {}", guide.getId());
            throw new GuideBusyException(GUIDE_BUSY.getMessage(), GUIDE_BUSY.getCode());
        }

        return true;
    }

}
