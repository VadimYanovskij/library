package com.senla.training.library.repository;

import com.senla.training.library.entity.Role;
import com.senla.training.library.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName (RoleName roleName);
}
