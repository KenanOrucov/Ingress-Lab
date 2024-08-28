package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.dao.entity.TravelerEntity;
import com.example.Ingress_lab.model.request.TravelerRequest;
import com.example.Ingress_lab.model.response.TravelerResponse;

import java.util.List;

public interface TravelerService {
    List<TravelerResponse> getAllTravelers();
    TravelerResponse getTravelerById(Long id);
    List<TravelerResponse> getTravelersByTourId(Long tourId);
    void createTraveler(TravelerRequest request);
    void updateTraveler(Long id, TravelerRequest request);
    void deleteTraveler(Long id);
    void clearCache();
    TravelerEntity fetchTravelerIfExist(Long id);
}
