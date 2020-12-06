package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Role;
import com.senla.training.library.enums.RoleName;
import com.senla.training.library.repository.RoleRepository;
import com.senla.training.library.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role findByRoleName(RoleName roleName) {
        log.info("Finding role with roleName = {} in database", roleName);
        Optional<Role> result = repository.findByRoleName(roleName);
        if (result.isPresent()) {
            log.info("Role with roleName = {} found in database successfully", roleName);
            return result.get();
        } else {
            throw new EntityNotFoundException("Role not found");
        }
    }

    @Override
    public Role findById(Integer id) {
        log.info("Finding role with id = {} in database", id);
        Optional<Role> result = repository.findById(id);
        if (result.isPresent()) {
            log.info("Role with id = {} found in database", id);
            return result.get();
        } else {
            throw new EntityNotFoundException("Role not found in database");
        }
    }
}
