package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.TravelerEntity;
import com.example.Ingress_lab.dao.repository.TravelerRepository;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.model.request.TravelerRequest;
import com.example.Ingress_lab.model.response.TravelerResponse;
import com.example.Ingress_lab.service.abstraction.TourService;
import com.example.Ingress_lab.service.abstraction.TravelerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.Ingress_lab.mapper.TravelerMapper.toTravelerEntity;
import static com.example.Ingress_lab.mapper.TravelerMapper.toTravelerResponse;
import static com.example.Ingress_lab.mapper.TravelerMapper.toTravelerResponses;
import static com.example.Ingress_lab.mapper.TravelerMapper.updateTravelerEntity;
import static com.example.Ingress_lab.model.enums.ExceptionConstants.TRAVELER_NOT_FOUND;
import static com.example.Ingress_lab.model.enums.EntityStatus.ACTIVE;
import static com.example.Ingress_lab.model.enums.EntityStatus.INACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelerServiceHandler implements TravelerService {
    private final TravelerRepository travelerRepository;
    private final TourService tourService;
//    private final CardClient cardClient;

    @Override
    public List<TravelerResponse> getAllTravelers() {
        log.info("ActionLog.getAllTravelers.start");
        return toTravelerResponses(travelerRepository.findAllByTravelerStatus(ACTIVE));
    }

    @Cacheable("travelers")
    @Override
    public TravelerResponse getTravelerById(Long id) {
        log.info("ActionLog.getTravelerById.start id: {}", id);
        var traveler = fetchTravelerIfExist(id);
        log.info("ActionLog.getTravelerById.success id: {}", id);
        return toTravelerResponse(traveler);
    }

    public List<TravelerResponse> getTravelersByTourId(Long tourId) {
        log.info("ActionLog.getTravelersByTourId.start tourId: {}", tourId);
        var travelers = travelerRepository.findByStatusAndTourId(tourId);
        log.info("ActionLog.getTravelersByTourId.success tourId: {}", tourId);
        return toTravelerResponses(travelers);
    }

    @Override
    @Transactional
    public void createTraveler(TravelerRequest request) {
        log.info("ActionLog.createTraveler.start request: {}", request);
        var traveler = toTravelerEntity(request);

//        var card = cardClient.getCardById(10L);
//        log.info("ActionLog.createTraveler.card is:{}", card);

        setToursToTraveler(request, traveler);

        log.info("ActionLog.createTraveler.success traveler: {}", traveler);
        travelerRepository.save(traveler);
    }

    @Override
    @Transactional
    public void updateTraveler(Long id, TravelerRequest request) {
        log.info("ActionLog.updateTraveler.start id: {}, request: {}", id, request);
        var traveler = fetchTravelerIfExist(id);
        updateTravelerEntity(traveler, request);

       setToursToTraveler(request, traveler);

        updateCache(id);
        travelerRepository.save(traveler);
        log.info("ActionLog.updateTraveler.success id: {}", id);
    }

    @Override
    @Transactional
    public void deleteTraveler(Long id) {
        log.info("ActionLog.deleteTraveler.start id: {}", id);
        var traveler = fetchTravelerIfExist(id);
        traveler.setTravelerStatus(INACTIVE);
        log.info("ActionLog.deleteTraveler.success id: {}", id);
        travelerRepository.save(traveler);
    }

    @CacheEvict(allEntries = true, value = "travelers")
    @Override
    public void clearCache() {
        log.info("ActionLog.clearCache.success");
    }

    @CachePut(value = "travelers")
    public void updateCache(Long id){
        log.info("ActionLog.updateCache.success id: {}", id);
        getTravelerById(id);
    }

    public TravelerEntity fetchTravelerIfExist(Long id) {
        return travelerRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(TRAVELER_NOT_FOUND.getCode(), TRAVELER_NOT_FOUND.getMessage()));
    }

    private void setToursToTraveler(TravelerRequest request, TravelerEntity traveler) {
        if (request.getTourIds() != null){
            log.info("ActionLog.updateTraveler.tourIds: {}", request.getTourIds());
            traveler.setTours(request
                    .getTourIds()
                    .stream()
                    .map(tourService::fetchTourIfExist)
                    .collect(Collectors.toSet()));
        }
    }
}
