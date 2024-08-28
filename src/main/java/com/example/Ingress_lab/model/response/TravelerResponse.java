package com.example.Ingress_lab.model.response;


import com.example.Ingress_lab.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private List<TourResponse> tours;
}
