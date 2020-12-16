package com.senla.training.library.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthenticationUserDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
