package com.senla.training.library.dto;

import com.senla.training.library.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BorrowHistoryDto {
    private Integer borrowId;
    private Integer userid;
    private String username;
    private Integer bookId;
    private String bookName;
    private Set<Author> bookAuthors;
    private Integer bookPublisherId;
    private String bookPublisherName;
    private Integer bookPublicationYear;
    private Integer bookCategoryId;
    private String bookCategoryName;
    private String bookStatusName;
    private LocalDate borrowDate;
    private LocalDate repaymentDate;
    private LocalDate returnDate;
}
