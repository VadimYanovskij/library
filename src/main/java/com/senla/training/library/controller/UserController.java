package com.senla.training.library.controller;

import com.senla.training.library.dto.converter.DtoConverter;
import com.senla.training.library.dto.UserDto;
import com.senla.training.library.entity.Role;
import com.senla.training.library.service.UserService;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final DtoConverter dtoConverter;

    public UserController(UserService userService, DtoConverter dtoConverter) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        log.info("Listing users");
        ResponseEntity<List<UserDto>> result = new ResponseEntity<>(
                dtoConverter.usersToDtos(
                        userService.findAll()
                ),
                HttpStatus.OK
        );
        log.info("Users listed successfully");
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Integer id) {
        log.info("Finding user with id = {}", id);
        ResponseEntity<UserDto> result = new ResponseEntity<>(
                dtoConverter.userToDto(
                        userService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("User with id = {} found", id);
        return result;
    }

    @PostMapping
    public ResponseEntity<UserDto> add(@Validated(New.class) @RequestBody UserDto userDto,
                                       BindingResult bindingResult) {
        log.info("Creating user: {}", userDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<UserDto> result = new ResponseEntity<>(
                dtoConverter.userToDto(
                        userService.add(
                                dtoConverter.userDtoToEntity(userDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("User created successfully with info: \" {}", userDto);
        return result;
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@Validated(Exist.class) @RequestBody UserDto userDto,
                                          BindingResult bindingResult) {
        log.info("Updating user: {}", userDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<UserDto> result = new ResponseEntity<>(
                dtoConverter.userToDto(
                        userService.update(
                                dtoConverter.userDtoToEntity(userDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("User updated successfully with info: \" {}", userDto);
        return result;
    }

    @PostMapping({"/setroles/{userName}"})
    public ResponseEntity<UserDto> setRoles(@PathVariable("userName") String userName,
                                            @RequestBody Set<Role> roles) {
        log.info("Setting roles: {} for user: {}", roles, userName);
        ResponseEntity<UserDto> result = new ResponseEntity<>(
                dtoConverter.userToDto(
                        userService.setRoles(userName, roles)
                ),
                HttpStatus.OK
        );
        log.info("Roles: {} for user: {} set successfully", roles, userName);
        return result;
    }
}
