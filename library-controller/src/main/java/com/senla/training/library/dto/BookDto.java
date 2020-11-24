package com.senla.training.library.dto;

import com.senla.training.library.entity.Author;
import com.senla.training.library.entity.Category;
import com.senla.training.library.entity.Publisher;
import com.senla.training.library.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookDto {

    @Null(groups = {New.class})
    @NotNull(groups = Exist.class)
    private Integer id;

    @NotNull(groups = {New.class, Exist.class})
    private String name;

    @NotNull(groups = {New.class, Exist.class})
    private Set<Author> authors;

    @NotNull(groups = {New.class, Exist.class})
    private Publisher publisher;

    @NotNull(groups = {New.class, Exist.class})
    private Integer publicationYear;

    @NotNull(groups = {New.class, Exist.class})
    private Category category;

    @NotNull(groups = {New.class, Exist.class})
    private BookStatus bookStatus;
}
