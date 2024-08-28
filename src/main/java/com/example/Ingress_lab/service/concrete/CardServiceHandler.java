//package com.example.Ingress_lab.service.concrete;
//
//import com.example.Ingress_lab.dao.entity.CardEntity;
//import com.example.Ingress_lab.dao.repository.CardRepository;
//import com.example.Ingress_lab.exception.NotFoundException;
//import com.example.Ingress_lab.model.criteria.CardCriteria;
//import com.example.Ingress_lab.model.criteria.PageCriteria;
//import com.example.Ingress_lab.model.request.CardRequest;
//import com.example.Ingress_lab.model.response.CardResponse;
//import com.example.Ingress_lab.model.response.PageableResponse;
//import com.example.Ingress_lab.service.abstraction.CardService;
//import com.example.Ingress_lab.service.specification.CardSpecification;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
//import static com.example.Ingress_lab.mapper.Mapper.*;
//import static com.example.Ingress_lab.model.enums.Status.ACTIVE;
//import static com.example.Ingress_lab.model.enums.Status.DELETED;
//import static com.example.Ingress_lab.model.enums.ExceptionConstants.CARD_NOT_FOUND;
//import static com.example.Ingress_lab.util.IdentifierUtil.IDENTIFIER_UTIL;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class CardServiceHandler implements CardService {
//    private final CardRepository cardRepository;
//
//    @Override
//    public void createCard(CardRequest request) {
//        log.info("ActionLog.createCard.start request: {}", request);
//        var card = toCardEntity(request);
//        card.setCardNumber(IDENTIFIER_UTIL.randomPan());
//        log.info("ActionLog.createCard.success card: {}", card);
//        cardRepository.save(card);
//    }
//
//    @Cacheable("cards")
//    @Override
//    public CardResponse getCardById(Long id) {
//        log.info("ActionLog.getCardById.start id: {}", id);
//        var card = fetchCardIfExist(id);
//        log.info("ActionLog.getCardById.success id: {}", id);
//        return toCardResponse(card);
//    }
//
//
//    @CacheEvict(allEntries = true, value = "cards")
//    @Override
//    public void clearCache() {
//        log.info("ActionLog.clearCache.success");
//    }
//
//    @Override
//    public PageableResponse getAllCards(PageCriteria pageCriteria, CardCriteria cardCriteria) {
//        log.info("ActionLog.getAllCards.start");
//        Page<CardEntity> cards = cardRepository.findAll(
//                new CardSpecification(cardCriteria),
//                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending()));
//
//        return mapToPageableResponse(cards);
//    }
//
//    @Override
//    public void updateCard(Long id, CardRequest request) {
//        log.info("ActionLog.updateCard.start id: {}, request: {}", id, request);
//        var entity = fetchCardIfExist(id);
//        updateCardEntity(entity, request);
//        cardRepository.save(entity);
//        updateCache(id);
//        log.info("ActionLog.updateCard.success id: {}", id);
//    }
//
//    @CachePut(value = "cards")
//    public CardResponse updateCache(Long id){
//        log.info("ActionLog.updateCache.success id: {}", id);
//        return getCardById(id);
//    }
//
//    @Override
//    public void deleteCardById(Long id) {
//        log.info("ActionLog.deleteCardById.start id: {}", id);
//        var card = fetchCardIfExist(id);
//        card.setStatus(DELETED);
//        log.info("ActionLog.deleteCardById.success id: {}", id);
//        cardRepository.save(card);
//    }
//
//    @Override
//    public void updateCardAmount() {
//        log.info("ActionLog.updateCardAmount.start");
//        var cards = cardRepository.findAllByStatus(ACTIVE);
//        cards.forEach(card -> card.setAmount(card.getAmount().multiply(BigDecimal.valueOf(1.05))));
//        cardRepository.saveAll(cards);
//        log.info("ActionLog.updateCardAmount.cards: {}", cards);
//        log.info("ActionLog.updateCardAmount.success");
//    }
//
//
//    private CardEntity fetchCardIfExist(Long id) {
//        return cardRepository
//                .findById(id)
//                .orElseThrow(() -> new NotFoundException(CARD_NOT_FOUND.getCode(), CARD_NOT_FOUND.getMessage()));
//    }
//
//}
