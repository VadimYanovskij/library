package com.senla.training.library.service;

import com.senla.training.library.entity.Role;
import com.senla.training.library.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService extends CommonService<User> {

    User findByUsername(String userName);

    List<Role> setRoles(String userName, Set<Integer> roleIds);

}
