package com.senla.training.library.controller;

import com.senla.training.library.dto.AuthenticationUserDto;
import com.senla.training.library.dto.TokenDto;
import com.senla.training.library.dto.UserForRegisterDto;
import com.senla.training.library.dto.converter.SecurityDtoConverter;
import com.senla.training.library.service.BlockedTokenService;
import com.senla.training.library.service.JwtTokenService;
import com.senla.training.library.service.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final BlockedTokenService blockedTokenService;
    private final SecurityDtoConverter securityDtoConverter;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenService jwtTokenUtil,
                                    JwtUserDetailsService userDetailsService,
                                    BlockedTokenService blockedTokenService,
                                    SecurityDtoConverter securityDtoConverter) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.blockedTokenService = blockedTokenService;
        this.securityDtoConverter = securityDtoConverter;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(
            @RequestBody AuthenticationUserDto authenticationUserDto) {
        log.info("Starting login user with username {}",
                authenticationUserDto.getUsername());
        final Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationUserDto.getUsername(),
                        authenticationUserDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info("User logged in successfully");
        return ResponseEntity.ok(new TokenDto(jwtTokenUtil.generateToken(auth)));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Validated @RequestBody UserForRegisterDto userForRegisterDto,
            BindingResult bindingResult) {
        log.info("Starting registering user with username {}", userForRegisterDto.getUsername());
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        userDetailsService.save(userForRegisterDto);
        ResponseEntity<String> result = new ResponseEntity<>(
                "User registered successfully", HttpStatus.OK);
        log.info("User registered successfully");
        return result;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody TokenDto tokenDto) {
        log.info("Starting logout with token {}", tokenDto.getToken());
        blockedTokenService.add(securityDtoConverter.tokenDtoToToken(tokenDto));
        ResponseEntity<String> result = new ResponseEntity<>(
                "logout successful", HttpStatus.OK);
        log.info("Logout successful");
        return result;
    }

}



