package com.example.Ingress_lab.model.response;

import com.example.Ingress_lab.model.enums.GuideStatus;
import com.example.Ingress_lab.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuideResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private GuideStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PassportResponse passport;
}
