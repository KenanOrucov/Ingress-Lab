package com.example.Ingress_lab.model.response;

import com.example.Ingress_lab.model.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassportResponse {
    private Long id;
    private String passportNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String country;
    private EntityStatus passportEntityStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
