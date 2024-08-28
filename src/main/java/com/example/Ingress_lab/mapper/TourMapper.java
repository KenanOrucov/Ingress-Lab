package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.TourEntity;
import com.example.Ingress_lab.model.request.TourRequest;
import com.example.Ingress_lab.model.response.TourResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Ingress_lab.mapper.GuideMapper.toGuideResponses;
import static com.example.Ingress_lab.model.enums.Status.ACTIVE;

public enum TourMapper {
    TOUR_MAPPER;

    public static TourEntity toTourEntity(TourRequest request) {
        return TourEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(ACTIVE)
                .build();
    }

    public static TourResponse toTourResponse(TourEntity entity) {
        return TourResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
//                .guides(toGuideResponses(entity
//                        .getGuides()
//                        .stream()
//                        .toList()))
                .build();
    }

    public static List<TourResponse> toTourResponses(List<TourEntity> entities) {
        return entities
                .stream()
                .map(TourMapper::toTourResponse)
                .collect(Collectors.toList());
    }

    public static void updateTourEntity(TourEntity entity, TourRequest request) {
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
