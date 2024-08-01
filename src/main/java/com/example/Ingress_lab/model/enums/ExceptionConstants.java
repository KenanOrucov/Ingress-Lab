package com.example.Ingress_lab.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ExceptionConstants {
    CARD_NOT_FOUND("CARD_NOT_FOUND", "Card not found."),
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred."),;
    private String code;
    private String message;
}
