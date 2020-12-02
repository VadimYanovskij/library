package com.senla.training.library.service;

import com.senla.training.library.entity.Role;
import com.senla.training.library.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    User add(User user);

    User update(User user);

    User findByUsername(String userName);

    List<Role> setRoles(String userName, Set<Integer> roleIds);

}
