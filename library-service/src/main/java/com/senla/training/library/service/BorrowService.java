package com.senla.training.library.service;

import com.senla.training.library.entity.Borrow;

import java.util.List;

public interface BorrowService {
    List<Borrow> findAll();

    Borrow findById(Integer id);

    Borrow add(Borrow borrow);

    Borrow update(Borrow borrow);

    List<Borrow> findAllByBookId(Integer bookId);

    List<Borrow> findExpiredBorrows();
}
