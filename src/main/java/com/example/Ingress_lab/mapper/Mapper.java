package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.CardEntity;
import com.example.Ingress_lab.model.enums.Status;
import com.example.Ingress_lab.model.request.CardRequest;
import com.example.Ingress_lab.model.response.CardResponse;
import com.example.Ingress_lab.model.response.PageableResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


public enum Mapper {
    CARD_MAPPER;

    public static CardEntity toCardEntity(CardRequest request) {
        return CardEntity.builder()
                .username(request.getUsername())
                .amount(request.getAmount())
                .status(Status.ACTIVE)
                .build();
    }

    public static CardResponse toCardResponse(CardEntity entity) {
        return CardResponse.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .cardNumber(entity.getCardNumber())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static List<CardResponse> toCardResponses(List<CardEntity> entities) {
        return entities
                .stream()
                .map(Mapper::toCardResponse)
                .toList();
    }

    public static void updateCardEntity(CardEntity entity, CardRequest request) {
        entity.setUsername(request.getUsername());
        entity.setAmount(request.getAmount());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    public static PageableResponse mapToPageableResponse(Page<CardEntity> entities) {
        return PageableResponse
                .builder()
                .content(Collections.singletonList(entities.map(Mapper::toCardResponse).stream().toList()))
                .totalElements(entities.getTotalElements())
                .hasNextPage(entities.hasNext())
                .lastPageNumber(entities.getTotalPages())
                .build();
    }
}

