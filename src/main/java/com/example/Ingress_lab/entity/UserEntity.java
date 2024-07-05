package com.example.Ingress_lab.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    Long id;
    String name;
    String email;
    List<Long> luckyNumbers;
}
