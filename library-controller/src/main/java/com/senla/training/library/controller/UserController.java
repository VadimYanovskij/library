package com.senla.training.library.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.senla.training.library.dto.RoleDto;
import com.senla.training.library.dto.UserDto;
import com.senla.training.library.dto.converter.RoleConverterDto;
import com.senla.training.library.dto.converter.UserConverterDto;
import com.senla.training.library.service.UserService;
import com.senla.training.library.transfer.AdminDetails;
import com.senla.training.library.transfer.Details;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
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
    private final UserConverterDto dtoConverter;
    private final RoleConverterDto roleConverterDto;

    public UserController(UserService userService, UserConverterDto dtoConverter,
                          RoleConverterDto roleConverterDto) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
        this.roleConverterDto = roleConverterDto;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    @JsonView({AdminDetails.class})
    public ResponseEntity<List<UserDto>> findAll() {
        log.info("Listing users");
        ResponseEntity<List<UserDto>> result = new ResponseEntity<>(
                dtoConverter.entitiesToDtos(
                        userService.findAll()
                ),
                HttpStatus.OK
        );
        log.info("Users listed successfully");
        return result;
    }

    @GetMapping("/{id}")
    @JsonView({AdminDetails.class})
    public ResponseEntity<UserDto> findById(@PathVariable("id") Integer id) {
        log.info("Finding user with id = {}", id);
        ResponseEntity<UserDto> result = new ResponseEntity<>(
                dtoConverter.entityToDto(
                        userService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("User with id = {} found", id);
        return result;
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @JsonView(Details.class)
    public ResponseEntity<UserDto> add(@Validated(New.class) @RequestBody UserDto userDto,
                                       BindingResult bindingResult) {
        log.info("Creating user: {}", userDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<UserDto> result = new ResponseEntity<>(
                dtoConverter.entityToDto(
                        userService.add(
                                dtoConverter.dtoToEntity(userDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("User created successfully with info: \" {}", userDto);
        return result;
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@Validated(Exist.class)@RequestBody UserDto userDto,
                                          BindingResult bindingResult) {
        log.info("Updating user: {}", userDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<UserDto> result = new ResponseEntity<>(
                dtoConverter.entityToDto(
                        userService.update(
                                dtoConverter.dtoToEntity(userDto)
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
                                                 @RequestBody List<RoleDto> roleDtos) {
        log.info("Setting roles: {} for user: {}", roleDtos, userName);
        ResponseEntity<List<RoleDto>> result = new ResponseEntity<>(
                roleConverterDto.entitiesToDtos(
                        userService.setRoles(
                                userName,
                                roleConverterDto.dtosToEntities(roleDtos)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Roles: {} for user: {} set successfully", roleDtos, userName);
        return result;
    }
}
