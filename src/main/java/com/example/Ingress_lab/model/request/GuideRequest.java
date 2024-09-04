package com.example.Ingress_lab.model.request;

import com.example.Ingress_lab.model.response.PassportResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private PassportRequest passport;
}
