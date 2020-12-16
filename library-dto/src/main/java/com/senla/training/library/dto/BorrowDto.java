package com.senla.training.library.dto;


import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BorrowDto {

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    private Integer id;

    @NotNull(groups = {New.class})
    private Integer userId;

    @NotNull(groups = {New.class})
    private Integer bookId;

    @NotNull(groups = {New.class})
    private LocalDate borrowDate;

    @NotNull(groups = {New.class})
    private LocalDate repaymentDate;

    private LocalDate returnDate;
}
