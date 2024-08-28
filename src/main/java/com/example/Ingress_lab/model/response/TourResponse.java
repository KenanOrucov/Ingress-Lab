package com.example.Ingress_lab.model.response;


import com.example.Ingress_lab.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private Set<DestinationResponse> destinations;
//    private List<GuideResponse> guides;
}
