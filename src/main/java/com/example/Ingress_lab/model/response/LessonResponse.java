package com.example.Ingress_lab.model.response;

import com.example.Ingress_lab.model.enums.LessonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {
    private Long id;
    private String name;
    private Integer hours;
    private Integer studentCount;
    private LessonStatus status;
}
