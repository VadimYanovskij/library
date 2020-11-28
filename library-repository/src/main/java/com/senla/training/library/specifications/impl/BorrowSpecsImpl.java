package com.senla.training.library.specifications.impl;

import com.senla.training.library.entity.Borrow;
import com.senla.training.library.enums.BookStatus;
import com.senla.training.library.specifications.BorrowSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BorrowSpecsImpl implements BorrowSpecs {

    @Override
    public Specification<Borrow> getBorrowsByBookStatus(BookStatus bookStatus) {
        return (Specification<Borrow>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("book").get("bookStatus"), bookStatus);
    }

    @Override
    public Specification<Borrow> getExpiredBorrows() {
        return (Specification<Borrow>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("returnDate"), root.get("repaymentDate"));
    }

}
