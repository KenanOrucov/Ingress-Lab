package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    @Qualifier("userServiceHandler")
    private final UserService userServiceHandler;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createUser(String username, String password) {
        userServiceHandler.createUser(username, password);
    }
}
