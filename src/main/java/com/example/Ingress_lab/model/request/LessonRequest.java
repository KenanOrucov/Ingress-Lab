package com.example.Ingress_lab.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {
    private String name;
    private Integer hours;
    private Integer studentCount;
}
