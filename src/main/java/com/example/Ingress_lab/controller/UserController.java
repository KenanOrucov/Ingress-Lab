package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.dto.UserDto;
import com.example.Ingress_lab.entity.UserEntity;
import com.example.Ingress_lab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;


    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/name/{name}/email/{email}")
    public UserDto getUserByNameAndEmail(@PathVariable String name, @PathVariable String email) {
        return userService.getUserByNameAndEmail(name, email);
    }

    @GetMapping("/lucky-number/{userId}/{luckNumber}")
    public Long getUserLuckyNumberSquare(@PathVariable Long userId, @PathVariable Long luckNumber) {
        return userService.getUserLuckyNumberSquare(userId, luckNumber);
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
