package com.senla.training.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.senla.training.library.transfer.AdminDetails;
import com.senla.training.library.transfer.Details;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import com.senla.training.library.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDto {

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    @JsonView({Details.class, AdminDetails.class})
    private Integer id;

    @NotNull(groups = {New.class})
    @JsonView({Details.class, AdminDetails.class})
    private String username;

    @NotNull(groups = {New.class})
    @JsonView({Details.class, AdminDetails.class})
    private String email;

    @JsonIgnore
    @NotNull(groups = {New.class})
    private String password;

    @NotNull(groups = {New.class})
    @JsonView({Details.class, AdminDetails.class})
    private String firstname;

    @NotNull(groups = {New.class})
    @JsonView({Details.class, AdminDetails.class})
    private String lastname;

    @NotNull(groups = {New.class})
    @JsonView({Details.class, AdminDetails.class})
    private LocalDate birthday;

    @JsonView({AdminDetails.class})
    @NotNull(groups = {New.class})
    private Set<Role> roles;
}
