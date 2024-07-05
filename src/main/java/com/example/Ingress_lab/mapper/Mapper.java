package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dto.UserDto;
import com.example.Ingress_lab.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public UserDto mapToUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .luckyNumbers(userEntity.getLuckyNumbers())
                .build();
    }

    public UserEntity mapToUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .luckyNumbers(userDto.getLuckyNumbers())
                .build();
    }

    public List<UserDto> mapToUserDtos(List<UserEntity> userEntities) {
        return userEntities
                .stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
