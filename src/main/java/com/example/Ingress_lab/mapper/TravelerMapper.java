package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.TravelerEntity;
import com.example.Ingress_lab.model.request.TravelerRequest;
import com.example.Ingress_lab.model.response.TravelerResponse;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.Ingress_lab.mapper.GuideMapper.toGuideResponses;
import static com.example.Ingress_lab.mapper.TourMapper.toTourResponses;
import static com.example.Ingress_lab.model.enums.Status.ACTIVE;

public enum TravelerMapper {
    TRAVELER_MAPPER;

    public static TravelerEntity toTravelerEntity(TravelerRequest request) {
        return TravelerEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .status(ACTIVE)
                .build();
    }

    public static TravelerResponse toTravelerResponse(TravelerEntity entity) {
        return TravelerResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
//                .tours(toTourResponses(entity
//                        .getTours()
//                        .stream()
//                        .toList()))
                .build();
    }

    public static List<TravelerResponse> toTravelerResponses(List<TravelerEntity> entities) {
        return entities
                .stream()
                .map(TravelerMapper::toTravelerResponse)
                .toList();
    }

    public static void updateTravelerEntity(TravelerEntity entity, TravelerRequest request) {
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
