package com.senla.training.library.dto.converter.impl;

import com.senla.training.library.dto.UserDto;
import com.senla.training.library.dto.converter.UserConverterDto;
import com.senla.training.library.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserConverterDtoImpl implements UserConverterDto {
    @Override
    public UserDto entityToDto(User user) {
        log.info("Converting user with id = {} to com.senla.training.library.dto", user.getId());
        UserDto result = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getBirthday(),
                user.getRoles()
        );
        log.info("User converted successfully");
        return result;
    }

    @Override
    public User dtoToEntity(UserDto userDto) {
        log.info("Converting userDto with id = {} to user", userDto.getId());
        User result = new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getBirthday(),
                userDto.getRoles()
        );
        log.info("UserDto converted successfully");
        return result;
    }

    @Override
    public List<UserDto> entitiesToDtos(List<User> users) {
        log.info("Converting users to dtos");
        List<UserDto> result = users.stream()
                .map(user -> entityToDto(user))
                .collect(Collectors.toList());
        log.info("Users converted successfully");
        return result;
    }

    @Override
    public List<User> dtosToEntities(List<UserDto> userDtos) {
        log.info("Converting userDtos to users");
        List<User> result = userDtos.stream()
                .map(userDto -> dtoToEntity(userDto))
                .collect(Collectors.toList());
        log.info("UserDtos converted successfully");
        return result;
    }
}
