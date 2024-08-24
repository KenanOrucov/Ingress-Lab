package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.model.criteria.CardCriteria;
import com.example.Ingress_lab.model.criteria.PageCriteria;
import com.example.Ingress_lab.model.request.CardRequest;
import com.example.Ingress_lab.model.response.CardResponse;
import com.example.Ingress_lab.model.response.PageableResponse;

public interface CardService {
    void createCard(CardRequest request);
    CardResponse getCardById(Long id);
    PageableResponse getAllCards(PageCriteria pageCriteria, CardCriteria cardCriteria);
    void updateCard(Long id, CardRequest request);
    void deleteCardById(Long id);
    void clearCache();
    void updateCardAmount();
}
