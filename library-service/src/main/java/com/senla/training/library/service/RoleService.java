package com.senla.training.library.service;

import com.senla.training.library.entity.Role;
import com.senla.training.library.enums.RoleName;

public interface RoleService {

    Role findByRoleName(RoleName roleName);

    Role findById(Integer id);

}
