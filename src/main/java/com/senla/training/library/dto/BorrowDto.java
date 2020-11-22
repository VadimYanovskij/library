package com.senla.training.library.dto;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.User;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BorrowDto {

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    private Integer id;

    @NotNull(groups = {New.class, Exist.class})
    private User user;

    @NotNull(groups = {New.class, Exist.class})
    private Book book;

    @NotNull(groups = {New.class, Exist.class})
    private LocalDate borrowDate;

    @NotNull(groups = {New.class, Exist.class})
    private LocalDate repaymentDate;

    private LocalDate returnDate;
}
