package com.example.Ingress_lab.model.response;

import com.example.Ingress_lab.model.enums.CardStatus;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {
    private Long id;
    private String userName;
    private BigDecimal amount;
    private BigInteger cardNumber;
    private CardStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
