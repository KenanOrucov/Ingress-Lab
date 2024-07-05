package com.example.Ingress_lab.service;

import com.example.Ingress_lab.dto.UserDto;
import com.example.Ingress_lab.entity.UserEntity;
import com.example.Ingress_lab.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults( makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {
    Mapper mapper;
    private List <UserEntity> users = new ArrayList<>();


    public List<UserDto> getAllUsers() {
        return mapper.mapToUserDtos(users);
    }

    public UserDto getUserById(Long id) {
        return mapper.mapToUserDto(users.get(id.intValue() - 1));
    }

    public UserDto getUserByNameAndEmail(String name, String email) {
        return mapper.mapToUserDto(users
                .stream()
                .filter(user -> user.getName().equalsIgnoreCase(name) && user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null));
    }

    public Long getUserLuckyNumberSquare(Long userId, Long luckNumber) {
        return users
                .stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .flatMap(user -> user.getLuckyNumbers()
                        .stream()
                        .filter(number -> number.equals(luckNumber))
                        .findFirst()
                        .map(number -> number * number))
                .orElse(null);
    }


    public void saveUser(UserDto user) {
        users.add(mapper.mapToUserEntity(user));
    }

    public void updateUser(Long id, UserDto user) {
        UserEntity userEntity = users.get(id.intValue() - 1);
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setLuckyNumbers(user.getLuckyNumbers());
    }

    public void deleteUser(Long id) {
        users.remove(id.intValue() - 1);
    }

}
