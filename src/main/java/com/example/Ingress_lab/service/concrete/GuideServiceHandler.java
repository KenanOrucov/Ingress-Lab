package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.GuideEntity;
import com.example.Ingress_lab.dao.repository.GuideRepository;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.model.enums.GuideStatus;
import com.example.Ingress_lab.model.request.GuideRequest;
import com.example.Ingress_lab.model.response.GuideResponse;
import com.example.Ingress_lab.service.abstraction.GuideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Ingress_lab.mapper.GuideMapper.toGuideEntity;
import static com.example.Ingress_lab.mapper.GuideMapper.toGuideResponse;
import static com.example.Ingress_lab.mapper.GuideMapper.toGuideResponses;
import static com.example.Ingress_lab.mapper.GuideMapper.updateGuideEntity;
import static com.example.Ingress_lab.mapper.PassportMapper.toPassportEntity;
import static com.example.Ingress_lab.mapper.PassportMapper.updatePassportEntity;
import static com.example.Ingress_lab.model.enums.ExceptionConstants.GUIDE_NOT_FOUND;
import static com.example.Ingress_lab.model.enums.GuideStatus.INACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideServiceHandler implements GuideService {
    private final GuideRepository guideRepository;

    @Override
    public List<GuideResponse> getAllGuides() {
        return toGuideResponses(guideRepository.findAllByStatusIsNot(INACTIVE));
    }

    @Cacheable("guides")
    @Override
    public GuideResponse getGuideById(Long id) {
        var guide = fetchGuideIfExist(id);
        return toGuideResponse(guide);
    }

    @Override
    public List<GuideResponse> getGuidesByTourId(Long tourId) {
        var guides = guideRepository.findGuidesByTourIdAndStatus(tourId);
        return toGuideResponses(guides);
    }

    @Override
    public List<GuideResponse> getAvailableGuides(LocalDate date) {
        var guide = guideRepository.findAvailableGuidesWithinDate();

        var availableGuides = guide.stream()
                .filter(g -> g.getTours().stream()
                        .noneMatch(tour -> tour.getStartDate().isBefore(date) && tour.getEndDate().isAfter(date)))
                .collect(Collectors.toList());


        return toGuideResponses(availableGuides);
    }

    @Override
    public void createGuide(GuideRequest request) {
        var guide = toGuideEntity(request);
        var passport = toPassportEntity(guide, request.getPassport());
        guide.setPassport(passport);
        guideRepository.save(guide);
    }

    @Override
    public void updateGuide(Long id, GuideRequest request) {
        var guide = fetchGuideIfExist(id);

        updateGuideEntity(guide, request);
        updatePassportEntity(guide.getPassport(), request.getPassport());

        updateCache(id);
        guideRepository.save(guide);
    }

    @Override
    public void deleteGuide(Long id) {
        var guide = fetchGuideIfExist(id);
        guide.setStatus(INACTIVE);
        guideRepository.save(guide);
    }

    @CacheEvict(allEntries = true, value = "guides")
    @Override
    public void clearCache() {
    }

    @CachePut(value = "cards")
    public void updateCache(Long id){
        getGuideById(id);
    }

    @Override
    public void changeGuideStatus(Long id, GuideStatus status) {
        var guide = fetchGuideIfExist(id);
        guide.setStatus(status);
        guideRepository.save(guide);
    }

    public GuideEntity fetchGuideIfExist(Long id) {
        var guide = guideRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(GUIDE_NOT_FOUND.getCode(), GUIDE_NOT_FOUND.getMessage()));

        if (guide.getStatus() == INACTIVE) {
            throw new NotFoundException(GUIDE_NOT_FOUND.getCode(), GUIDE_NOT_FOUND.getMessage());
        }
        return guide;
    }

}
