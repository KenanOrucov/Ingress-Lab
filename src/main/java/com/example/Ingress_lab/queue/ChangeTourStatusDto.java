package com.example.Ingress_lab.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeTourStatusDto {
    private Long id;
    private String status;
}
