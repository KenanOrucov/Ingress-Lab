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
import static com.example.Ingress_lab.model.enums.ExceptionMessages.GUIDE_NOT_FOUND;
import static com.example.Ingress_lab.model.enums.GuideStatus.INACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideServiceHandler implements GuideService {
    private final GuideRepository guideRepository;

    @Override
    public List<GuideResponse> getAllGuides() {
        log.info("ActionLog.getAllGuides.start");
        return toGuideResponses(guideRepository.findAllByStatusIsNot(INACTIVE));
    }

    @Cacheable("guides")
    @Override
    public GuideResponse getGuideById(Long id) {
        log.info("ActionLog.getGuideById.start id: {}", id);
        var guide = fetchGuideIfExist(id);
        log.info("ActionLog.getGuideById.success id: {}", id);
        return toGuideResponse(guide);
    }

    @Override
    public List<GuideResponse> getGuidesByTourId(Long tourId) {
        log.info("ActionLog.getGuidesByTourId.start tourId: {}", tourId);
        var guides = guideRepository.findGuidesByTourIdAndStatus(tourId);
        log.info("guides: {}", guides);

        log.info("ActionLog.getGuidesByTourId.success tourId: {}", tourId);
        return toGuideResponses(guides);
    }

    @Override
    public List<GuideResponse> getAvailableGuides(LocalDate date) {
        log.info("ActionLog.getAvailableGuides.start");
        var guide = guideRepository.findAvailableGuidesWithinDate();

        var availableGuides = guide.stream()
                .filter(g -> g.getTours().stream()
                        .noneMatch(tour -> tour.getStartDate().isBefore(date) && tour.getEndDate().isAfter(date)))
                .collect(Collectors.toList());


        return toGuideResponses(availableGuides);
    }

    @Override
    public void createGuide(GuideRequest request) {
        log.info("ActionLog.createGuide.start request: {}", request);
        var guide = toGuideEntity(request);
        var passport = toPassportEntity(guide, request.getPassport());

        guide.setPassport(passport);

        log.info("ActionLog.createGuide.success guide: {}", guide);
        guideRepository.save(guide);
    }

    @Override
    public void updateGuide(Long id, GuideRequest request) {
        log.info("ActionLog.updateGuide.start id: {}, request: {}", id, request);
        var guide = fetchGuideIfExist(id);

        updateGuideEntity(guide, request);
        updatePassportEntity(guide.getPassport(), request.getPassport());

        updateCache(id);
        guideRepository.save(guide);
        log.info("ActionLog.updateGuide.success id: {}", id);
    }

    @Override
    public void deleteGuide(Long id) {
        log.info("ActionLog.deleteGuide.start id: {}", id);
        var guide = fetchGuideIfExist(id);
        guide.setStatus(INACTIVE);
        log.info("ActionLog.deleteGuide.success id: {}", id);
        guideRepository.save(guide);
    }

    @CacheEvict(allEntries = true, value = "guides")
    @Override
    public void clearCache() {
        log.info("ActionLog.clearCache.success");
    }

    @CachePut(value = "cards")
    public void updateCache(Long id){
        log.info("ActionLog.updateCache.success id: {}", id);
        getGuideById(id);
    }

    @Override
    public void changeGuideStatus(Long id, GuideStatus status) {
        log.info("ActionLog.changeGuideStatus.start id: {}, status: {}", id, status);
        var guide = fetchGuideIfExist(id);
        guide.setStatus(status);
        guideRepository.save(guide);
        log.info("ActionLog.changeGuideStatus.success id: {}", id);
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
