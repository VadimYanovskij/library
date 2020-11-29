package com.senla.training.library.specifications.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.enums.BookStatus;
import com.senla.training.library.specifications.BookSpecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookSpecsImpl implements BookSpecs {

    @Override
    public Specification<Book> getBooksByBookStatus(BookStatus bookStatus) {
        log.info("Using the specification getBooksByBookStatus");
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("bookStatus"), bookStatus);
    }
}
