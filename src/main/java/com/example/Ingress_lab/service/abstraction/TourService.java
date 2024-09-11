package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.dao.entity.TourEntity;
import com.example.Ingress_lab.model.request.TourRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;
import com.example.Ingress_lab.model.response.TourResponse;

import java.util.List;

public interface TourService {
    List<TourResponse> getAllTours();
    TourResponse getTourById(Long id);
    List<TourResponse> getToursByTravelerId(Long travelerId);
    void createTour(TourRequest request);
    void updateTour(Long id, TourRequest request);
    void deleteTour(Long id);

    void clearCache();
    TourEntity fetchTourIfExist(Long id);

}
