package com.senla.training.library.specifications;

import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.entity.Borrow;
import com.senla.training.library.enums.BookStatusName;
import org.springframework.data.jpa.domain.Specification;

public interface BorrowSpecs {

    Specification<Borrow> getBorrowsByBookStatus(BookStatus bookStatus);

    Specification<Borrow> getExpiredBorrows();
}
