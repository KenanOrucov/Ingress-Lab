package com.example.Ingress_lab.dao;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Component
public class CustomerEntity {
    private Long id;
    private String company;
    private UserEntity user;
}
