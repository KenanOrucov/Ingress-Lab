package com.example.Ingress_lab.model.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {
    private Long id;
    private String username;
    private BigDecimal amount;
    private String cardNumber;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
