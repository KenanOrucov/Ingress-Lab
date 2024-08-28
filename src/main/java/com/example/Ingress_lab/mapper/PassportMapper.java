package com.example.Ingress_lab.mapper;


import com.example.Ingress_lab.dao.entity.GuideEntity;
import com.example.Ingress_lab.dao.entity.PassportEntity;
import com.example.Ingress_lab.model.request.PassportRequest;
import com.example.Ingress_lab.model.response.PassportResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Ingress_lab.model.enums.Status.ACTIVE;

public enum PassportMapper {
    PASSPORT_MAPPER;

    public static PassportEntity toPassportEntity(GuideEntity guide, PassportRequest request) {
        return PassportEntity.builder()
                .passportNumber(request.getPassportNumber())
                .issueDate(request.getIssueDate())
                .expiryDate(request.getExpiryDate())
                .country(request.getCountry())
                .guide(guide)
                .status(ACTIVE)
                .build();
    }

    public static PassportResponse toPassportResponse(PassportEntity entity) {
        return PassportResponse.builder()
                .id(entity.getId())
                .passportNumber(entity.getPassportNumber())
                .issueDate(entity.getIssueDate())
                .expiryDate(entity.getExpiryDate())
                .country(entity.getCountry())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static List<PassportResponse> toPassportResponses(List<PassportEntity> entities) {
        return entities
                .stream()
                .map(PassportMapper::toPassportResponse)
                .collect(Collectors.toList());
    }

    public static void updatePassportEntity(PassportEntity entity, PassportRequest request) {
        entity.setPassportNumber(request.getPassportNumber());
        entity.setIssueDate(request.getIssueDate());
        entity.setExpiryDate(request.getExpiryDate());
        entity.setCountry(request.getCountry());
        entity.setUpdatedAt(LocalDateTime.now());
    }

}

