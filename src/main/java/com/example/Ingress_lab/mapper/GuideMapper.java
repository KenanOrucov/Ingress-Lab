package com.example.Ingress_lab.mapper;


import com.example.Ingress_lab.dao.entity.GuideEntity;
import com.example.Ingress_lab.model.request.GuideRequest;
import com.example.Ingress_lab.model.response.GuideResponse;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.Ingress_lab.mapper.PassportMapper.toPassportResponse;
import static com.example.Ingress_lab.model.enums.GuideStatus.FREE;
import static com.example.Ingress_lab.model.enums.Status.ACTIVE;

public enum GuideMapper {
    GUIDE_MAPPER;

    public static GuideEntity toGuideEntity(GuideRequest request) {
        return GuideEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .status(FREE)
                .build();
    }

    public static GuideResponse toGuideResponse(GuideEntity entity) {
        return GuideResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .passport(toPassportResponse(entity.getPassport()))
                .build();
    }

    public static List<GuideResponse> toGuideResponses(List<GuideEntity> entities) {
        return entities
                .stream()
                .map(GuideMapper::toGuideResponse)
                .toList();
    }

    public static void updateGuideEntity(GuideEntity entity, GuideRequest request) {
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setUpdatedAt(LocalDateTime.now());
    }

}

