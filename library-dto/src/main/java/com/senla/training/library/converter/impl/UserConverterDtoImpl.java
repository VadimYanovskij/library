package com.senla.training.library.converter.impl;

import com.senla.training.library.dto.UserDto;
import com.senla.training.library.converter.UserConverterDto;
import com.senla.training.library.entity.User;
import com.senla.training.library.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserConverterDtoImpl implements UserConverterDto {

    private final RoleService roleService;

    public UserConverterDtoImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UserDto entityToDto(User user) {
        log.info("Converting user with id = {} to com.senla.training.library.dto", user.getId());
        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());
        result.setFirstname(user.getFirstname());
        result.setLastname(user.getLastname());
        result.setBirthday(user.getBirthday());
        result.setRoleIds(user.getRoles().stream()
                .map(role -> role.getId()).collect(Collectors.toSet()));
        log.info("User converted successfully");
        return result;
    }

    @Override
    public User dtoToEntity(UserDto userDto) {
        log.info("Converting userDto with id = {} to user", userDto.getId());
        User result = new User();
        result.setId(userDto.getId());
        result.setUsername(userDto.getUsername());
        result.setEmail(userDto.getEmail());
        result.setPassword(userDto.getPassword());
        result.setFirstname(userDto.getFirstname());
        result.setLastname(userDto.getLastname());
        result.setBirthday(userDto.getBirthday());
        result.setRoles(userDto.getRoleIds().stream()
                .map(id -> roleService.findById(id))
                .collect(Collectors.toSet())
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
