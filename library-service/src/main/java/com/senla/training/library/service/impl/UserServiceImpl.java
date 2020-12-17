package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Role;
import com.senla.training.library.entity.User;
import com.senla.training.library.repository.UserRepository;
import com.senla.training.library.service.RoleService;
import com.senla.training.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vadim Yanovskij
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    /**
     * Finds all users in the database
     *
     * @return list of User
     */
    @Override
    public List<User> findAll() {
        log.info("Listing users from database");
        List<User> result = userRepository.findAll();
        log.info("Users listed successfully from database");
        return result;
    }

    /**
     * Finds a user in the database by id.
     * If user not found throw EntityNotFoundException.
     *
     * @return User
     */
    @Override
    public User findById(Integer id) {
        log.info("Finding user with id = {} in database", id);
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            log.info("User with id = {} found in database", id);
            return result.get();
        } else {
            throw new EntityNotFoundException("User not found in database");
        }
    }

    /**
     * Save the user to the database
     *
     * @return saved User with id
     */
    @Override
    public User add(User user) {
        log.info("Creating in database user with username = {}", user.getUsername());
        User result = userRepository.save(user);
        log.info("User created in database successfully");
        return result;
    }

    /**
     * Update the user info in the database.
     * If user not found throw EntityNotFoundException.
     * Username and roles cannot be changed. Throw IllegalArgumentException.
     *
     * @return updated User
     */
    @Override
    public User update(User user) {
        log.info("Updating in database user with id = {}", user.getId());
        Optional<User> userForUpdate = userRepository.findById(user.getId());
        if (userForUpdate.isPresent()) {
            User updatedUser = userForUpdate.get();
            if (user.getEmail() != null) {
                updatedUser.setEmail(user.getEmail());
            }
            if (user.getFirstname() != null) {
                updatedUser.setFirstname(user.getFirstname());
            }
            if (user.getLastname() != null) {
                updatedUser.setLastname(user.getLastname());
            }
            if (user.getBirthday() != null) {
                updatedUser.setBirthday(user.getBirthday());
            }
            if (user.getUsername() != null) {
                throw new IllegalArgumentException("You can't change username there");
            }
            if (user.getRoles() != null) {
                throw new IllegalArgumentException("You can't change roles there");
            }
            User result = userRepository.save(updatedUser);
            log.info("User updated successfully");
            return result;
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }

    /**
     * Finds a user in the database by username.
     * If user not found throw EntityNotFoundException.
     *
     * @return User
     */
    @Override
    public User findByUsername(String userName) {
        log.info("Finding user with userName = {} in database", userName);
        Optional<User> result = userRepository.findByUsername(userName);
        if (result.isPresent()) {
            log.info("User with userName = {} found in database successfully", userName);
            return result.get();
        } else {
            throw new EntityNotFoundException("User not found");
        }

    }

    /**
     * Performs a quick check to see what roles the new user could be assigned to.
     *
     * @return list of roles for the user
     */
    @Override
    public List<Role> setRoles(String userName, Set<Integer> roleIds) {
        log.info("Setting roles: {} for user: {} in database", roleIds, userName);
        User user = findByUsername(userName);
        user.setRoles(roleIds.stream()
                .map(id -> roleService.findById(id))
                .collect(Collectors.toSet())
        );
        User result = userRepository.save(user);
        log.info("Roles: {} for user: {} set in database successfully", roleIds, userName);
        return new ArrayList<>(result.getRoles());
    }
}
