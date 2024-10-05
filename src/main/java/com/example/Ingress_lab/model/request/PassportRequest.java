package com.example.Ingress_lab.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassportRequest {
    private String passportNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String country;

}
