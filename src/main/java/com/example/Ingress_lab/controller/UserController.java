package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.UserEntity;
import com.example.Ingress_lab.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true ,level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @PostMapping
    public UserEntity saveUser(@RequestBody UserEntity user) {
       return userService.saveUser(user);
    }
}
