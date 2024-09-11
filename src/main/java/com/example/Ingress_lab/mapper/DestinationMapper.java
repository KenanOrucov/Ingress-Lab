package com.example.Ingress_lab.mapper;


import com.example.Ingress_lab.dao.entity.DestinationEntity;
import com.example.Ingress_lab.model.cache.DestinationCacheData;
import com.example.Ingress_lab.model.request.DestinationRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.Ingress_lab.model.enums.EntityStatus.ACTIVE;

public enum DestinationMapper {
    DESTINATION_MAPPER;

    public static DestinationEntity toDestinationEntity(DestinationRequest request) {
        return DestinationEntity.builder()
                .location(request.getLocation())
                .description(request.getDescription())
                .visitDate(request.getVisitDate())
                .destinationStatus(ACTIVE)
                .build();
    }

    public static DestinationResponse toDestinationResponse(DestinationEntity entity) {
        return DestinationResponse.builder()
                .id(entity.getId())
                .location(entity.getLocation())
                .description(entity.getDescription())
                .visitDate(entity.getVisitDate())
                .destinationEntityStatus(entity.getDestinationStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static List<DestinationResponse> toDestinationResponses(List<DestinationEntity> entities) {
        return entities
                .stream()
                .map(DestinationMapper::toDestinationResponse)
                .toList();
    }

    public static void updateDestinationEntity(DestinationEntity entity, DestinationRequest request) {
        entity.setLocation(request.getLocation());
        entity.setDescription(request.getDescription());
        entity.setVisitDate(request.getVisitDate());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    public static DestinationCacheData toDestinationCacheData(DestinationEntity entity) {
        return DestinationCacheData.builder()
                .id(entity.getId())
                .location(entity.getLocation())
                .description(entity.getDescription())
                .visitDate(entity.getVisitDate())
                .destinationEntityStatus(entity.getDestinationStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}

