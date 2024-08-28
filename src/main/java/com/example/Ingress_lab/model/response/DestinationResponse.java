package com.example.Ingress_lab.model.response;

import com.example.Ingress_lab.model.enums.Status;
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
public class DestinationResponse {
    private Long id;
    private String location;
    private String description;
    private LocalDate visitDate;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private TourResponse tour;
}
