package com.senla.training.library.specifications;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.enums.BookStatusName;
import org.springframework.data.jpa.domain.Specification;

public interface BookSpecs {
    Specification<Book> getBooksByBookStatusId(BookStatus bookStatus);
}
