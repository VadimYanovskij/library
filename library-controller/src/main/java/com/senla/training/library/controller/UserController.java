package com.senla.training.library.controller;

import com.senla.training.library.converter.RoleConverterDto;
import com.senla.training.library.converter.UserConverterDto;
import com.senla.training.library.dto.RoleDto;
import com.senla.training.library.dto.UserDto;
import com.senla.training.library.service.UserService;
import com.senla.training.library.transfer.Exist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;
    private final UserConverterDto userConverterDto;
    private final RoleConverterDto roleConverterDto;

    public UserController(UserService userService,
                          UserConverterDto userConverterDto,
                          RoleConverterDto roleConverterDto) {
        this.userService = userService;
        this.userConverterDto = userConverterDto;
        this.roleConverterDto = roleConverterDto;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<UserDto>> findAll() {
        log.info("Listing users");
        ResponseEntity<List<UserDto>> result = new ResponseEntity<>(
                userConverterDto.entitiesToDtos(
                        userService.findAll()
                ),
                HttpStatus.OK
        );
        log.info("Users listed successfully");
        return result;
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Integer id) {
        log.info("Finding user with id = {}", id);
        ResponseEntity<UserDto> result = new ResponseEntity<>(
                userConverterDto.entityToDto(
                        userService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("User with id = {} found", id);
        return result;
    }

    @PutMapping
    public ResponseEntity<UserDto> update(
            @Validated(Exist.class) @RequestBody UserDto userDto,
            BindingResult bindingResult) {
        log.info("Updating user: {}", userDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<UserDto> result = new ResponseEntity<>(
                userConverterDto.entityToDto(
                        userService.update(
                                userConverterDto.dtoToEntity(userDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("User updated successfully with info: \" {}", userDto);
        return result;
    }

    @Secured("ROLE_ADMIN")
    @PutMapping({"/setroles/{userName}"})
    public ResponseEntity<List<RoleDto>> setRoles(@PathVariable("userName") String userName,
                                                  @RequestBody Set<Integer> roleIds) {
        log.info("Setting roles: {} for user: {}", roleIds, userName);
        ResponseEntity<List<RoleDto>> result = new ResponseEntity<>(
                roleConverterDto.entitiesToDtos(
                        userService.setRoles(userName, roleIds)),
                HttpStatus.OK
        );
        log.info("Roles: {} for user: {} set successfully", roleIds, userName);
        return result;
    }
}
