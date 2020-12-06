package com.senla.training.library.repository;

import com.senla.training.library.entity.Role;
import com.senla.training.library.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName (RoleName roleName);
}
