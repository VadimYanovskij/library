package com.senla.training.library.dto;

import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookEditDto {

    @Null(groups = {New.class})
    @NotNull(groups = Exist.class)
    private Integer id;

    @NotNull(groups = {New.class})
    private String name;

    @NotNull(groups = {New.class})
    private Set<Integer> authorIds;

    @NotNull(groups = {New.class})
    private Integer publisherId;

    @NotNull(groups = {New.class})
    private Integer publicationYear;

    @NotNull(groups = {New.class})
    private Integer categoryId;

    @NotNull(groups = {New.class})
    private Integer bookStatusId;

}
