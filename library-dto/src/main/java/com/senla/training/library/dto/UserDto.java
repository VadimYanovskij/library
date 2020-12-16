package com.senla.training.library.dto;

import com.senla.training.library.transfer.Exist;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(groups = {Exist.class})
    private Integer id;

    private String username;

    @Email(groups = {Exist.class})
    private String email;

    private String firstname;

    private String lastname;

    private LocalDate birthday;

    private Set<Integer> roleIds;
}
