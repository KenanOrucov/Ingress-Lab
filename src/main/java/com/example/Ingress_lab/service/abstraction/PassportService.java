package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.dao.entity.PassportEntity;
import com.example.Ingress_lab.model.request.PassportRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;
import com.example.Ingress_lab.model.response.PassportResponse;

import java.util.List;

public interface PassportService {
    List<PassportResponse> getAllPassports();
    PassportResponse getPassportById(Long id);
    void createPassport(PassportRequest request);
    void updatePassport(Long id, PassportRequest request);
    void deletePassport(Long id);
    void clearCache();
    PassportEntity fetchPassportIfExist(Long id);

}
