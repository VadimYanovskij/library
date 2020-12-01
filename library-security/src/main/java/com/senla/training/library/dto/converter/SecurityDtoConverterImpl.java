package com.senla.training.library.dto.converter;

import com.senla.training.library.dto.TokenDto;
import com.senla.training.library.dto.UserForRegisterDto;
import com.senla.training.library.entity.Role;
import com.senla.training.library.entity.Token;
import com.senla.training.library.entity.User;
import com.senla.training.library.enums.RoleName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Slf4j
@Component
public class SecurityDtoConverterImpl implements SecurityDtoConverter {

    private final PasswordEncoder bcryptEncoder;

    public SecurityDtoConverterImpl(PasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public User userForRegisterDtoToUser(UserForRegisterDto userForRegisterDto) {
        User result = new User();
        result.setUsername(userForRegisterDto.getUsername());
        result.setEmail(userForRegisterDto.getEmail());
        result.setPassword(bcryptEncoder.encode(userForRegisterDto.getPassword()));
        result.setFirstname(userForRegisterDto.getFirstname());
        result.setLastname(userForRegisterDto.getLastname());
        result.setBirthday(userForRegisterDto.getBirthday());
        result.setRoles(new HashSet<>(Arrays.asList(new Role(1, RoleName.ROLE_USER))));
        return result;
    }

    @Override
    public Token tokenDtoToToken(TokenDto tokenDto) {
        log.info("Converting TokenDto to Token");
        Token result = new Token(tokenDto.getToken());
        log.info("Token converted successfully");
        return result;
    }
}
