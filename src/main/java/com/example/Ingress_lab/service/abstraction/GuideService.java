package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.dao.entity.GuideEntity;
import com.example.Ingress_lab.model.enums.GuideStatus;
import com.example.Ingress_lab.model.request.GuideRequest;
import com.example.Ingress_lab.model.response.GuideResponse;

import java.time.LocalDate;
import java.util.List;

public interface GuideService {
    List<GuideResponse> getAllGuides();
    GuideResponse getGuideById(Long id);
    List<GuideResponse> getGuidesByTourId(Long travelerId);
    List<GuideResponse> getAvailableGuides(LocalDate date);
    void createGuide(GuideRequest request);
    void updateGuide(Long id, GuideRequest request);
    void deleteGuide(Long id);
    void changeGuideStatus(Long id, GuideStatus status);
    void clearCache();
    GuideEntity fetchGuideIfExist(Long id);
}
