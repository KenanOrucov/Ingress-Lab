package com.example.Ingress_lab.dao.entity;

import com.example.Ingress_lab.model.enums.LessonStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "lessons")
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer hours;
    private Integer studentCount;
    @Enumerated(EnumType.STRING)
    private LessonStatus status;

}
