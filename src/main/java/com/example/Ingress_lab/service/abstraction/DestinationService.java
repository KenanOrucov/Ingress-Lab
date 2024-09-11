package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.dao.entity.DestinationEntity;
import com.example.Ingress_lab.model.cache.DestinationCacheData;
import com.example.Ingress_lab.model.request.DestinationRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;

import java.util.List;

public interface DestinationService {
    List<DestinationResponse> getAllDestinations();
    DestinationCacheData getDestinationById(Long id);
    List<DestinationResponse> getDestinationsByTourId(Long tourId);
    void createDestination(DestinationRequest request);
    void updateDestination(Long id, DestinationRequest request);
    void deleteDestination(Long id);
    DestinationEntity fetchDestinationIfExist(Long id);
}
