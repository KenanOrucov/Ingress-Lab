package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.DestinationEntity;
import com.example.Ingress_lab.dao.repository.DestinationRepository;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.model.request.DestinationRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;
import com.example.Ingress_lab.service.abstraction.DestinationService;
import com.example.Ingress_lab.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.Ingress_lab.mapper.DestinationMapper.toDestinationEntity;
import static com.example.Ingress_lab.mapper.DestinationMapper.toDestinationResponse;
import static com.example.Ingress_lab.mapper.DestinationMapper.toDestinationResponses;
import static com.example.Ingress_lab.mapper.DestinationMapper.updateDestinationEntity;
import static com.example.Ingress_lab.model.enums.ExceptionConstants.DESTINATION_NOT_FOUND;
import static com.example.Ingress_lab.model.enums.Status.ACTIVE;
import static com.example.Ingress_lab.model.enums.Status.INACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class DestinationServiceHandler implements DestinationService {
    private final DestinationRepository destinationRepository;
    private final TourService tourService;

    @Override
    public List<DestinationResponse> getAllDestinations() {
        log.info("ActionLog.getAllDestinations.start");
        return toDestinationResponses(destinationRepository.findAllByStatus(ACTIVE));
    }

    @Cacheable("destinations")
    @Override
    public DestinationResponse getDestinationById(Long id) {
        log.info("ActionLog.getCardById.start id: {}", id);
        var destination = fetchDestinationIfExist(id);
        log.info("ActionLog.getCardById.success id: {}", id);
        return toDestinationResponse(destination);
    }

    @Override
    public List<DestinationResponse> getDestinationsByTourId(Long tourId) {
        log.info("ActionLog.getDestinationsByTourId.start tourId: {}", tourId);
        var destinations = destinationRepository.findByStatusAndTourId(ACTIVE, tourId);
        log.info("ActionLog.getDestinationsByTourId.success tourId: {}", tourId);
        return toDestinationResponses(destinations);
    }

    @Override
    public void createDestination(DestinationRequest request) {
        log.info("ActionLog.createDestination.start request: {}", request);
        var destination = toDestinationEntity(request);

        setTourToDestination(request, destination);

        log.info("ActionLog.createDestination.success destination: {}", destination);
        destinationRepository.save(destination);
    }

    @Override
    @Transactional
    public void updateDestination(Long id, DestinationRequest request) {
        log.info("ActionLog.updateDestination.start id: {}, request: {}", id, request);
        var destination = fetchDestinationIfExist(id);
        updateDestinationEntity(destination, request);

        setTourToDestination(request, destination);

        updateCache(id);
        destination.setTour(tourService.fetchTourIfExist(request.getTourId()));
        destinationRepository.save(destination);
        log.info("ActionLog.updateDestination.success id: {}", id);
    }

    @Override
    @Transactional
    public void deleteDestination(Long id) {
        log.info("ActionLog.deleteDestination.start id: {}", id);
        var destination = fetchDestinationIfExist(id);
        destination.setStatus(INACTIVE);
        log.info("ActionLog.deleteDestination.success id: {}", id);
        destinationRepository.save(destination);
    }

    @CacheEvict(allEntries = true, value = "destinations")
    @Override
    public void clearCache() {
        log.info("ActionLog.clearCache.success");
    }

    @CachePut(value = "destinations")
    public void updateCache(Long id){
        log.info("ActionLog.updateCache.success id: {}", id);
        getDestinationById(id);
    }

    @Override
    public DestinationEntity fetchDestinationIfExist(Long id) {
        var destination = destinationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(DESTINATION_NOT_FOUND.getCode(), DESTINATION_NOT_FOUND.getMessage()));

        if (destination.getStatus() == INACTIVE) {
            throw new NotFoundException(DESTINATION_NOT_FOUND.getCode(), DESTINATION_NOT_FOUND.getMessage());
        }

        return destination;
    }

    private void setTourToDestination(DestinationRequest request, DestinationEntity destination) {
        if (request.getTourId() != null){
            log.info("ActionLog.updateDestination.tourId: {}", request.getTourId());
            destination.setTour(tourService.fetchTourIfExist(request.getTourId()));
        }
    }

}
