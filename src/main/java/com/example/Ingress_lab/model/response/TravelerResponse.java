package com.example.Ingress_lab.model.response;


import com.example.Ingress_lab.model.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private EntityStatus travelerEntityStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
