package com.senla.training.library.specifications.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.enums.BookStatus;
import com.senla.training.library.specifications.BookSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecsImpl implements BookSpecs {

    @Override
    public Specification<Book> getBooksByBookStatus(BookStatus bookStatus) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("bookStatus"), bookStatus);
    }
}
