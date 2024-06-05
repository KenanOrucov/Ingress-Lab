package com.example.Ingress_lab.service;

import com.example.Ingress_lab.dao.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserEntity user;

    public UserEntity fillUserData() {
        user.setId(1L);
        user.setName("kanan");
        user.setEmail("test@mail.com");
        return user;
    }

    public UserEntity getUser() {
        return user;
    }
}
