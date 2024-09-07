package com.example.Ingress_lab.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record ErrorResponse(String code, String message) {

}
