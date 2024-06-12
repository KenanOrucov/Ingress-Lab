package com.example.Ingress_lab.service;

import com.example.Ingress_lab.model.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults( makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {

    public UserEntity saveUser(UserEntity user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
