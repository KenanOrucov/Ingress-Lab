package com.example.Ingress_lab.assignment.service;

import com.example.Ingress_lab.assignment.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults( makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class UserService {
    static Map<String, UserEntity> users = new HashMap<>();


    public List<UserEntity> getUserWithAge(Integer age){
//         users
//                .values()
//                .stream()
//                .filter(user -> user.getAge().equals(age))
//                .findFirst()
//                .orElse(null);

        if (age == null){
            return users
                    .values()
                    .stream()
                    .toList();
        }

        return users.values().stream().filter(user -> user.getAge().equals(age)).toList();
    }

    public void updateUserAge(String id, Integer age){
        UserEntity user = users.get(id);
        user.setAge(age);
    }

    public void addUser(UserEntity userEntity){
        String id = String.valueOf(UUID.randomUUID());
        userEntity.setId(id);
        users.put(id, userEntity);
    }
}
