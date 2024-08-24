package com.example.Ingress_lab.model.response;

import com.example.Ingress_lab.model.enums.Status;
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
public class CardResponse {
    private Long id;
    private String username;
    private BigDecimal amount;
    private String cardNumber;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
