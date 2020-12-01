package com.senla.training.library.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserForRegisterDto {
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
}
