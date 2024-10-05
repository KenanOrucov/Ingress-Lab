package com.example.Ingress_lab.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationRequest {
    private String location;
    private String description;
    private LocalDate visitDate;
    private Long tourId;
}
