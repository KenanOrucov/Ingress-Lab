package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.CardEntity;
import com.example.Ingress_lab.model.criteria.PageCriteria;
import com.example.Ingress_lab.model.enums.CardStatus;
import com.example.Ingress_lab.model.request.CardRequest;
import com.example.Ingress_lab.model.response.CardResponse;
import com.example.Ingress_lab.model.response.PageableCardResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


public enum Mapper {
    CARD_MAPPER;

    public CardEntity toCardEntity(CardRequest request) {
        return CardEntity.builder()
                .userName(request.getUserName())
                .amount(request.getAmount())
                .status(CardStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public CardResponse toCardResponse(CardEntity entity) {
        return CardResponse.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .cardNumber(entity.getCardNumber())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public List<CardResponse> toCardResponses(List<CardEntity> entities) {
        return entities
                .stream()
                .map(this::toCardResponse)
                .toList();
    }

    public CardEntity updateCard(CardEntity entity, CardRequest request) {
        entity.setUserName(request.getUserName());
        entity.setAmount(request.getAmount());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public PageableCardResponse mapToPageableResponse(Page<CardEntity> entities) {
        return PageableCardResponse
                .builder()
                .content(Collections.singletonList(entities.map(this::toCardResponse).stream().toList()))
                .totalElements(entities.getTotalElements())
                .hasNextPage(entities.hasNext())
                .lastPageNumber(entities.getTotalPages())
                .build();
    }
}

