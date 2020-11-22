package com.senla.training.library.dto;

import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AuthorDto {

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    private Integer id;

    @NotNull(groups = {New.class, Exist.class})
    private String firstname;

    @NotNull(groups = {New.class, Exist.class})
    private String lastname;
}
