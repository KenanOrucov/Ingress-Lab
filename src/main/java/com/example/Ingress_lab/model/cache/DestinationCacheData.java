package com.example.Ingress_lab.model.cache;

import com.example.Ingress_lab.model.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinationCacheData implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String location;
    private String description;
    private LocalDate visitDate;
    private EntityStatus destinationEntityStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
