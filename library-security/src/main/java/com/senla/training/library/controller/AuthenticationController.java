package com.senla.training.library.controller;

import com.senla.training.library.converter.SecurityDtoConverter;
import com.senla.training.library.dto.AuthenticationUserDto;
import com.senla.training.library.dto.TokenDto;
import com.senla.training.library.dto.UserForRegisterDto;
import com.senla.training.library.service.BlockedTokenService;
import com.senla.training.library.service.JwtTokenService;
import com.senla.training.library.service.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final BlockedTokenService blockedTokenService;
    private final SecurityDtoConverter securityDtoConverter;
    private final MessageSource messageSource;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenService jwtTokenUtil,
                                    JwtUserDetailsService userDetailsService,
                                    BlockedTokenService blockedTokenService,
                                    SecurityDtoConverter securityDtoConverter,
                                    MessageSource messageSource) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.blockedTokenService = blockedTokenService;
        this.securityDtoConverter = securityDtoConverter;
        this.messageSource = messageSource;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(
            @Validated @RequestBody AuthenticationUserDto authenticationUserDto,
            BindingResult bindingResult) {
        log.info("Starting login user with username {}",
                authenticationUserDto.getUsername());
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
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
            BindingResult bindingResult,
            @RequestParam(value = "locale", required = false) Locale locale) {
        log.info("Starting registering user with username {}",
                userForRegisterDto.getUsername());
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        userDetailsService.save(userForRegisterDto);
        ResponseEntity<String> result = new ResponseEntity<>(messageSource.getMessage(
                "label.UserRegistered", null, locale), HttpStatus.OK);
        log.info("User registered successfully");
        return result;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody TokenDto tokenDto,
                                         @RequestParam(value = "locale",
                                                 required = false) Locale locale) {
        log.info("Starting logout with token {}", tokenDto.getToken());
        blockedTokenService.add(securityDtoConverter.tokenDtoToToken(tokenDto));
        ResponseEntity<String> result = new ResponseEntity<>(messageSource.getMessage(
                "label.Logout", null, locale), HttpStatus.OK);
        log.info("Logout successful");
        return result;
    }

}



