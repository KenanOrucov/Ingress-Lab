package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.repository.CardRepository;
import com.example.Ingress_lab.model.request.CardRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Random;

import static com.example.Ingress_lab.mapper.Mapper.CARD_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionalCardServiceHandler {
    private final CardRepository cardRepository;

    @Transactional
    public void createCard(CardRequest request) {
        log.info("ActionLog.createCard.start request: {}", request);
        var card = CARD_MAPPER.toCardEntity(request);
        card.setCardNumber(generateCardNumber());
        log.info("ActionLog.createCard.success card: {}", card);
        cardRepository.save(card);
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
