package com.senla.training.library.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.senla.training.library.transfer.AdminDetails;
import com.senla.training.library.transfer.Details;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import com.senla.training.library.enums.RoleName;
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
public class RoleDto {

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    @JsonView({Details.class, AdminDetails.class})
    private Integer id;

    @NotNull(groups = {New.class, Exist.class})
    @JsonView({Details.class, AdminDetails.class})
    private RoleName roleName;
}
