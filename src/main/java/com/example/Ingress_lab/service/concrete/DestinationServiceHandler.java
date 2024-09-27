package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.DestinationEntity;
import com.example.Ingress_lab.dao.repository.DestinationRepository;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.model.cache.DestinationCacheData;
import com.example.Ingress_lab.model.request.DestinationRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;
import com.example.Ingress_lab.service.abstraction.DestinationService;
import com.example.Ingress_lab.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.Ingress_lab.mapper.DestinationMapper.toDestinationCacheData;
import static com.example.Ingress_lab.mapper.DestinationMapper.toDestinationEntity;
import static com.example.Ingress_lab.mapper.DestinationMapper.toDestinationResponses;
import static com.example.Ingress_lab.mapper.DestinationMapper.updateDestinationEntity;
import static com.example.Ingress_lab.model.enums.Constants.CACHE_KEY;
import static com.example.Ingress_lab.model.enums.ExceptionMessages.DESTINATION_NOT_FOUND;
import static com.example.Ingress_lab.model.enums.EntityStatus.ACTIVE;
import static com.example.Ingress_lab.model.enums.EntityStatus.INACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class DestinationServiceHandler implements DestinationService {
    private final DestinationRepository destinationRepository;
    private final TourService tourService;
    private final CacheServiceHandler cacheServiceHandler;
    @Override
    public List<DestinationResponse> getAllDestinations() {
        log.info("ActionLog.getAllDestinations.start");
        return toDestinationResponses(destinationRepository.findAllByDestinationStatus(ACTIVE));
    }

    @Override
    public DestinationCacheData getDestinationById(Long id) {
        log.info("ActionLog.getCardById.start id: {}", id);
        String cacheKeyReal = CACHE_KEY + "destination-id:" + id;

        var cachedData = (DestinationCacheData) cacheServiceHandler.get(cacheKeyReal);

        if (cachedData != null) {
            return cachedData;
        }

        var data = fetchDestinationIfExist(id);
        saveToCache(data);

        log.info("ActionLog.getCardById.success id: {}", id);
        return toDestinationCacheData(data);
    }

    @Override
    public List<DestinationResponse> getDestinationsByTourId(Long tourId) {
        log.info("ActionLog.getDestinationsByTourId.start tourId: {}", tourId);
        var destinations = destinationRepository.findByDestinationStatusAndTourId(ACTIVE, tourId);
        log.info("ActionLog.getDestinationsByTourId.success tourId: {}", tourId);
        return toDestinationResponses(destinations);
    }

    @Override
    public void createDestination(DestinationRequest request) {
        log.info("ActionLog.createDestination.start request: {}", request);
        var destination = toDestinationEntity(request);

        setTourToDestination(request, destination);

        log.info("ActionLog.createDestination.success destination: {}", destination);
    }

    @Override
    @Transactional
    public void updateDestination(Long id, DestinationRequest request) {
        log.info("ActionLog.updateDestination.start id: {}, request: {}", id, request);
        var destination = fetchDestinationIfExist(id);
        updateDestinationEntity(destination, request);

        setTourToDestination(request, destination);

        destination.setTour(tourService.fetchTourIfExist(request.getTourId()));
        destinationRepository.save(destination);

        log.info("ActionLog.updateDestination.success id: {}", id);
    }

    @Override
    @Transactional
    public void deleteDestination(Long id) {
        log.info("ActionLog.deleteDestination.start id: {}", id);
        var destination = fetchDestinationIfExist(id);
        destination.setDestinationStatus(INACTIVE);
        log.info("ActionLog.deleteDestination.success id: {}", id);
        destinationRepository.save(destination);
    }

    @Override
    public DestinationEntity fetchDestinationIfExist(Long id) {
        var destination = destinationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(DESTINATION_NOT_FOUND.getCode(), DESTINATION_NOT_FOUND.getMessage()));

        if (destination.getDestinationStatus() == INACTIVE) {
            throw new NotFoundException(DESTINATION_NOT_FOUND.getCode(), DESTINATION_NOT_FOUND.getMessage());
        }

        return destination;
    }

    private void saveToCache(DestinationEntity destination){
        String cacheKeyReal = CACHE_KEY + "destination-id:" + destination.getId();
        cacheServiceHandler.save(toDestinationCacheData(destination), cacheKeyReal);
    }

    private void setTourToDestination(DestinationRequest request, DestinationEntity destination) {
        if (request.getTourId() != null){
            log.info("ActionLog.updateDestination.tourId: {}", request.getTourId());
            destination.setTour(tourService.fetchTourIfExist(request.getTourId()));
        }
    }
}
