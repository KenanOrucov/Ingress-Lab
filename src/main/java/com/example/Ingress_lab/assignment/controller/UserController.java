package com.example.Ingress_lab.assignment.controller;

import com.example.Ingress_lab.assignment.entity.UserEntity;
import com.example.Ingress_lab.assignment.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/users")
@FieldDefaults( makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;


    @GetMapping
    public List<UserEntity> getUserWithAge(@RequestParam(required = false) Integer age){
        return userService.getUserWithAge(age);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserEntity userEntity){
        userService.addUser(userEntity);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserAge(@PathVariable String id, @RequestParam Integer age){
        userService.updateUserAge(id, age);
    }
}
