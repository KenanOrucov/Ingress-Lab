package com.example.Ingress_lab.dao;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Component
public class UserEntity {
    private Long id;
    private String name;
    private String email;
}
