package com.example.Ingress_lab.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCriteria {
    private BigDecimal amountFrom;
    private BigDecimal amountTo;
    private String username;
}
