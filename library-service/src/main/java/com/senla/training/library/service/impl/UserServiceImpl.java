package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Role;
import com.senla.training.library.entity.User;
import com.senla.training.library.repository.UserRepository;
import com.senla.training.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        log.info("Listing users from database");
        List<User> result = userRepository.findAll();
        log.info("Users listed successfully from database");
        return result;
    }

    @Override
    public User findById(Integer id) {
        log.info("Finding user with id = {} in database", id);
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            log.info("User with id = {} found in database", id);
            return result.get();
        } else {
            log.error("User with id = {} not found in database", id);
            throw new EntityNotFoundException("User not found in database");
        }
    }

    @Override
    public User add(User user) {
        log.info("Creating in database user with username = {}", user.getUsername());
        User result = userRepository.save(user);
        log.info("User created in database successfully");
        return result;
    }

    @Override
    public User update(User user) {
        log.info("Updating in database user with id = {}", user.getId());
        if (userRepository.findById(user.getId()).isPresent()) {
            User result = userRepository.save(user);
            log.info("User updated successfully in database with info: \" {}", user);
            return result;
        } else {
            log.error("User with id = {} not found ", user.getId());
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public User findByUsername(String userName) {
        log.info("Finding user with userName = {} in database", userName);
        User result = userRepository.findByUsername(userName);
        if (result == null) {
            log.error("User with userName = {} not found in database", userName);
            throw new EntityNotFoundException("User not found");
        }
        log.info("User with userName = {} found in database successfully", userName);
        return result;
    }

    @Override
    public User setRoles(String userName, Set<Role> roles) {
        log.info("Setting roles: {} for user: {} in database", roles, userName);
        User user = userRepository.findByUsername(userName);
        user.setRoles(roles);
        User result = userRepository.save(user);
        log.info("Roles: {} for user: {} set in database successfully", roles, userName);
        return result;
    }
}
