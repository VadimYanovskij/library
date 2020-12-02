package com.senla.training.library.dto.converter.impl;

import com.senla.training.library.dto.RoleDto;
import com.senla.training.library.dto.converter.RoleConverterDto;
import com.senla.training.library.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RoleConverterDtoImpl implements RoleConverterDto {

    @Override
    public RoleDto entityToDto(Role role) {
        log.info("Converting role with id = {} to roleDto", role.getId());
        RoleDto result = new RoleDto(
                role.getId(),
                role.getRoleName()
        );
        log.info("Role converted successfully");
        return result;
    }

    @Override
    public Role dtoToEntity(RoleDto roleDto) {
        log.info("Converting roleDto with id = {} to role", roleDto.getId());
        Role result = new Role(
                roleDto.getId(),
                roleDto.getRoleName()
        );
        log.info("RoleDto converted successfully");
        return result;
    }

    @Override
    public List<RoleDto> entitiesToDtos(List<Role> roles) {
        log.info("Converting roles to roleDtos");
        List<RoleDto> result = roles.stream()
                .map(role -> entityToDto(role))
                .collect(Collectors.toList());
        log.info("Roles converted successfully");
        return result;
    }

    @Override
    public List<Role> dtosToEntities(List<RoleDto> roleDtos) {
        log.info("Converting roleDtos to roles");
        List<Role> result = roleDtos.stream()
                .map(roleDto -> dtoToEntity(roleDto))
                .collect(Collectors.toList());
        log.info("RoleDtos converted successfully");
        return result;
    }
}
