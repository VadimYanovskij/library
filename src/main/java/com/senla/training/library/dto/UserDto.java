package com.senla.training.library.dto;

import com.senla.training.library.entity.Role;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
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
    private Integer id;

    @NotNull(groups = {New.class, Exist.class})
    private String username;

    @NotNull(groups = {New.class, Exist.class})
    private String email;

    @NotNull(groups = {New.class, Exist.class})
    private String password;

    @NotNull(groups = {New.class, Exist.class})
    private String firstname;

    @NotNull(groups = {New.class, Exist.class})
    private String lastname;

    @NotNull(groups = {New.class, Exist.class})
    private LocalDate birthday;

    @NotNull(groups = {New.class, Exist.class})
    private Set<Role> roles;
}
