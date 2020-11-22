package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Role;
import com.senla.training.library.entity.User;
import com.senla.training.library.repository.UserRepository;
import com.senla.training.library.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        if (!userRepository.findById(user.getId()).isPresent()) {
            System.out.println("Not exist " + user.getId());
            throw new EntityNotFoundException("User not found");
        }
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public User setRoles(String userName, Set<Role> roles) {
        User user = userRepository.findByUsername(userName);
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
