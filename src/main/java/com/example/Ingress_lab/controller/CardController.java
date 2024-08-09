package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.criteria.CardCriteria;
import com.example.Ingress_lab.model.criteria.PageCriteria;
import com.example.Ingress_lab.model.request.CardRequest;
import com.example.Ingress_lab.model.response.CardResponse;
import com.example.Ingress_lab.model.response.PageableCardResponse;
import com.example.Ingress_lab.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cards")
public class CardController {
    private final CardService cardService;

    @GetMapping
    PageableCardResponse getAllCards(PageCriteria pageCriteria,
                                     CardCriteria cardCriteria) {
        return cardService.getAllCards(pageCriteria, cardCriteria);
    }

    @GetMapping("/{id}")
    public CardResponse getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @PostMapping("/test")
    @ResponseStatus(ACCEPTED)
    public void test(){
        cardService.test();
    }

    @GetMapping("/test")
    @ResponseStatus(ACCEPTED)
    public void test2(){
        cardService.test2();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateAmount(@PathVariable Long id) {
        cardService.updateCardAmountTest(id);
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
}
