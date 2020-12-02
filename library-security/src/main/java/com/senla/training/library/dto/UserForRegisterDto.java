package com.senla.training.library.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserForRegisterDto {

    @NotNull
    private String username;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private LocalDate birthday;
}
