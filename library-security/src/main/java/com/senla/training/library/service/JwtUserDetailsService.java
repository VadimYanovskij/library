package com.senla.training.library.service;

import com.senla.training.library.converter.SecurityDtoConverter;
import com.senla.training.library.dto.UserForRegisterDto;
import com.senla.training.library.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final SecurityDtoConverter securityDtoConverter;

    public JwtUserDetailsService(UserService userService,
                                 SecurityDtoConverter securityDtoConverter) {
        this.userService = userService;
        this.securityDtoConverter = securityDtoConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        log.info("Start loading user by username = {}", username);
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        UserDetails result = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRoles().stream()
                        .map(role -> role.getRoleName().toString())
                        .toArray(String[]::new)
                )
        );
        log.info("User has loaded successfully");
        return result;
    }

    public void save(UserForRegisterDto userForRegisterDto) {
        log.info("Start saving new user, username = {}", userForRegisterDto.getUsername());
        userService.add(securityDtoConverter.userForRegisterDtoToUser(userForRegisterDto));
        log.info("New user has saved successfully");
    }
}
