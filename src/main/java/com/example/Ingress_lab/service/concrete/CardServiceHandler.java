package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.CardEntity;
import com.example.Ingress_lab.dao.repository.CardRepository;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.model.criteria.CardCriteria;
import com.example.Ingress_lab.model.criteria.PageCriteria;
import com.example.Ingress_lab.model.request.CardRequest;
import com.example.Ingress_lab.model.response.CardResponse;
import com.example.Ingress_lab.model.response.PageableCardResponse;
import com.example.Ingress_lab.service.abstraction.CardService;
import com.example.Ingress_lab.service.specification.CardSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import static com.example.Ingress_lab.mapper.Mapper.CARD_MAPPER;
import static com.example.Ingress_lab.model.enums.CardStatus.ACTIVE;
import static com.example.Ingress_lab.model.enums.CardStatus.INACTIVE;
import static com.example.Ingress_lab.model.enums.ExceptionConstants.CARD_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceHandler implements CardService {
    private final CardRepository cardRepository;

    @Override
    public void createCard(CardRequest request) {
        log.info("ActionLog.createCard.start request: {}", request);
        var card = CARD_MAPPER.toCardEntity(request);
        card.setCardNumber(generateCardNumber());
        log.info("ActionLog.createCard.success card: {}", card);
        cardRepository.save(card);
    }

    @Override
    public CardResponse getCardById(Long id) {
        log.info("ActionLog.getCardById.start id: {}", id);
        var card = fetchCardIfExist(id);
        log.info("ActionLog.getCardById.success id: {}", id);
        return CARD_MAPPER.toCardResponse(card);
    }

    @Override
    public PageableCardResponse getAllCards(PageCriteria pageCriteria, CardCriteria cardCriteria) {
        log.info("ActionLog.getAllCards.start");
        Page<CardEntity> cards = cardRepository.findAll(
                new CardSpecification(cardCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending()));

        return CARD_MAPPER.mapToPageableResponse(cards);
    }

    @Override
    public void updateCard(Long id, CardRequest request) {
        log.info("ActionLog.updateCard.start id: {}, request: {}", id, request);
        cardRepository.save(
                CARD_MAPPER
                        .updateCard(fetchCardIfExist(id), request));
    }

    @Override
    public void deleteCardById(Long id) {
        log.info("ActionLog.deleteCardById.start id: {}", id);
        var card = fetchCardIfExist(id);
        card.setStatus(INACTIVE);
        log.info("ActionLog.deleteCardById.success id: {}", id);
        cardRepository.save(card);
    }

    @Override
    public void updateCardAmount() {
        log.info("ActionLog.updateCardAmount.start");
        var cards = cardRepository.findByStatus(ACTIVE);
        cards.forEach(card -> card.setAmount(card.getAmount().multiply(BigDecimal.valueOf(1.05))));
        cardRepository.saveAll(cards);
        log.info("ActionLog.updateCardAmount.cards: {}", cards);
        log.info("ActionLog.updateCardAmount.success");
    }

    private CardEntity fetchCardIfExist(Long id) {
        return cardRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND.getCode(), CARD_NOT_FOUND.getMessage()));
    }

    private BigInteger generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
        }

        return new BigInteger(cardNumber.toString());
    }
}
