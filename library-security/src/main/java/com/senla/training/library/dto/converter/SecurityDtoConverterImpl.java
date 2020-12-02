package com.senla.training.library.dto.converter;

import com.senla.training.library.dto.TokenDto;
import com.senla.training.library.dto.UserForRegisterDto;
import com.senla.training.library.entity.Token;
import com.senla.training.library.entity.User;
import com.senla.training.library.enums.RoleName;
import com.senla.training.library.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Slf4j
@Component
public class SecurityDtoConverterImpl implements SecurityDtoConverter {

    private final PasswordEncoder bcryptEncoder;
    private final RoleService roleService;

    public SecurityDtoConverterImpl(PasswordEncoder bcryptEncoder,
                                    RoleService roleService) {
        this.bcryptEncoder = bcryptEncoder;
        this.roleService = roleService;
    }

    @Override
    public User userForRegisterDtoToUser(UserForRegisterDto userForRegisterDto) {
        log.info("Converting userForRegisterDto to user");
        User result = new User();
        result.setUsername(userForRegisterDto.getUsername());
        result.setEmail(userForRegisterDto.getEmail());
        result.setPassword(bcryptEncoder.encode(userForRegisterDto.getPassword()));
        result.setFirstname(userForRegisterDto.getFirstname());
        result.setLastname(userForRegisterDto.getLastname());
        result.setBirthday(userForRegisterDto.getBirthday());
        result.setRoles(new HashSet<>(Arrays.asList(roleService.findByRoleName(
                RoleName.ROLE_USER))));
        log.info("UserForRegisterDto to user converted successfully");
        return result;
    }

    @Override
    public Token tokenDtoToToken(TokenDto tokenDto) {
        log.info("Converting TokenDto to Token");
        Token result = new Token(tokenDto.getToken());
        log.info("TokenDto to Token converted successfully");
        return result;
    }
}
