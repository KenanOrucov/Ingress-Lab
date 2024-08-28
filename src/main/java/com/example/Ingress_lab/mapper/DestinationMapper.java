package com.example.Ingress_lab.mapper;


import com.example.Ingress_lab.dao.entity.DestinationEntity;
import com.example.Ingress_lab.model.request.DestinationRequest;
import com.example.Ingress_lab.model.response.DestinationResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Ingress_lab.mapper.TourMapper.toTourResponse;
import static com.example.Ingress_lab.model.enums.Status.ACTIVE;

public enum DestinationMapper {
    DESTINATION_MAPPER;

    public static DestinationEntity toDestinationEntity(DestinationRequest request) {
        return DestinationEntity.builder()
                .location(request.getLocation())
                .description(request.getDescription())
                .visitDate(request.getVisitDate())
                .status(ACTIVE)
                .build();
    }

    public static DestinationResponse toDestinationResponse(DestinationEntity entity) {
        return DestinationResponse.builder()
                .id(entity.getId())
                .location(entity.getLocation())
                .description(entity.getDescription())
                .visitDate(entity.getVisitDate())
                .status(entity.getStatus())
//                .tour(toTourResponse(entity.getTour()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static List<DestinationResponse> toDestinationResponses(List<DestinationEntity> entities) {
        return entities
                .stream()
                .map(DestinationMapper::toDestinationResponse)
                .collect(Collectors.toList());
    }

    public static void updateDestinationEntity(DestinationEntity entity, DestinationRequest request) {
        entity.setLocation(request.getLocation());
        entity.setDescription(request.getDescription());
        entity.setVisitDate(request.getVisitDate());
        entity.setUpdatedAt(LocalDateTime.now());
    }

//    public static CardEntity toCardEntity(CardRequest request) {
//        return CardEntity.builder()
//                .username(request.getUsername())
//                .amount(request.getAmount())
//                .status(Status.ACTIVE)
//                .build();
//    }
//
//    public static CardResponse toCardResponse(CardEntity entity) {
//        return CardResponse.builder()
//                .id(entity.getId())
//                .username(entity.getUsername())
//                .cardNumber(entity.getCardNumber())
//                .amount(entity.getAmount())
//                .status(entity.getStatus())
//                .createdAt(entity.getCreatedAt())
//                .updatedAt(entity.getUpdatedAt())
//                .build();
//    }
//
//    public static List<CardResponse> toCardResponses(List<CardEntity> entities) {
//        return entities
//                .stream()
//                .map(Mapper::toCardResponse)
//                .toList();
//    }
//
//    public static void updateCardEntity(CardEntity entity, CardRequest request) {
//        entity.setUsername(request.getUsername());
//        entity.setAmount(request.getAmount());
//        entity.setUpdatedAt(LocalDateTime.now());
//    }
//
//    public static PageableResponse mapToPageableResponse(Page<CardEntity> entities) {
//        return PageableResponse
//                .builder()
//                .content(Collections.singletonList(entities.map(Mapper::toCardResponse).stream().toList()))
//                .totalElements(entities.getTotalElements())
//                .hasNextPage(entities.hasNext())
//                .lastPageNumber(entities.getTotalPages())
//                .build();
//    }
}

