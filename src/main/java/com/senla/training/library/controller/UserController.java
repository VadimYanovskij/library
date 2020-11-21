package com.senla.training.library.controller;

import com.senla.training.library.entity.Role;
import com.senla.training.library.entity.User;
import com.senla.training.library.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
     return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById (@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> add(User user){
        return new ResponseEntity<>(userService.add(user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> update(User user){
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @PostMapping({"/setroles/{userName}"})
    public ResponseEntity<User> setRoles(@PathVariable("userName") String userName, Set<Role> roles){
        return new ResponseEntity<>(userService.setRoles(userName, roles), HttpStatus.OK);
    }
}
