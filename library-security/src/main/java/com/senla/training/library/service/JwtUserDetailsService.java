package com.senla.training.library.service;

import com.senla.training.library.dto.AuthenticationUserDto;
import com.senla.training.library.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final PasswordEncoder bcryptEncoder;

    public JwtUserDetailsService(UserService userService,
                                 PasswordEncoder bcryptEncoder) {
        this.userService = userService;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        log.info("Start loading user by username = {}", username);
        User user = userService.findByUsername(username);
        log.info("User = {}", user);
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
        log.info("UserDetails = {}", result);
        log.info("User has loaded successfully");
        return result;
    }

    public void save(AuthenticationUserDto authenticationUserDto) {
        log.info("Start saving new user");
        User newUser = new User();
        newUser.setUsername(authenticationUserDto.getUsername());
        newUser.setPassword(bcryptEncoder.encode(authenticationUserDto.getPassword()));
        userService.add(newUser);
        log.info("New user has saved successfully");
    }
}
