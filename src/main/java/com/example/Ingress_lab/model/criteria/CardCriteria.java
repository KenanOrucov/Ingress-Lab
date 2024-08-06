package com.example.Ingress_lab.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCriteria {
    private Double amountFrom;
    private Double amountTo;
    private String userName;
}
