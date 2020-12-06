package com.senla.training.library.service;

import com.senla.training.library.entity.Borrow;

import java.util.List;

public interface BorrowService extends CommonService<Borrow>{

    List<Borrow> findAllByBookId(Integer bookId);

    List<Borrow> findExpiredBorrows();
}
