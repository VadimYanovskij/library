package com.senla.training.library.specifications.impl;

import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.entity.Borrow;
import com.senla.training.library.enums.BookStatusName;
import com.senla.training.library.specifications.BorrowSpecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BorrowSpecsImpl implements BorrowSpecs {

    @Override
    public Specification<Borrow> getBorrowsByBookStatus(BookStatus bookStatus) {
        log.info("Using the specification getBorrowsByBookStatus, bookStatus = {}", bookStatus);
        return (Specification<Borrow>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("book").get("bookStatus"), bookStatus);
    }

    @Override
    public Specification<Borrow> getExpiredBorrows() {
        log.info("Using the specification getExpiredBorrows");
        return (Specification<Borrow>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("returnDate"), root.get("repaymentDate"));
    }

}
