package com.senla.training.library.specifications.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.enums.BookStatusName;
import com.senla.training.library.repository.BookStatusRepository;
import com.senla.training.library.specifications.BookSpecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookSpecsImpl implements BookSpecs {

    @Override
    public Specification<Book> getBooksByBookStatusId(BookStatus bookStatus) {
        log.info("Using the specification getBooksByBookStatus");
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("bookStatus"), bookStatus.getId());
    }
}
