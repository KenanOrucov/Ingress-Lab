package com.example.Ingress_lab.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public record ErrorResponse(String code, String message) {
}
