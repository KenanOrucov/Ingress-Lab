package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.model.enums.CardStatus;
import com.example.Ingress_lab.model.request.CardRequest;
import com.example.Ingress_lab.model.response.CardResponse;

import java.util.List;

public interface CardService {
    void createCard(CardRequest request);
    CardResponse getCardById(Long id);
    List<CardResponse> getAllCards();
    void updateCard(Long id, CardRequest request);
    void deleteCardById(Long id);
    void updateCardAmount();
}
