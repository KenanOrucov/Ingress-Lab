package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.criteria.CardCriteria;
import com.example.Ingress_lab.model.criteria.PageCriteria;
import com.example.Ingress_lab.model.request.CardRequest;
import com.example.Ingress_lab.model.response.CardResponse;
import com.example.Ingress_lab.model.response.PageableResponse;
import com.example.Ingress_lab.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cards")
public class CardController {
    private final CardService cardService;

    @GetMapping
    PageableResponse getAllCards(PageCriteria pageCriteria,
                                 CardCriteria cardCriteria) {
        return cardService.getAllCards(pageCriteria, cardCriteria);
    }

    @GetMapping("/{id}")
    public CardResponse getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCard(@RequestBody CardRequest request) {
        cardService.createCard(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateCard(@PathVariable Long id, @RequestBody CardRequest request) {
        cardService.updateCard(id, request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCardById(@PathVariable Long id) {
        cardService.deleteCardById(id);
    }

    @DeleteMapping("/cache")
    @ResponseStatus(NO_CONTENT)
    public void deleteCache() {
        cardService.clearCache();
    }

}
