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
public class CategoryDto {

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    private Integer id;

    private Integer parentId;

    @NotNull(groups = {New.class, Exist.class})
    private String categoryName;

}
