package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.Borrow;
import com.senla.training.library.repository.BookRepository;
import com.senla.training.library.repository.BorrowRepository;
import com.senla.training.library.service.BorrowService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;

    public BorrowServiceImpl(BorrowRepository borrowRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Borrow> findAll() {
        return borrowRepository.findAll();
    }

    @Override
    public Borrow findById(Integer id) {
        return borrowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Borrow not found"));
    }

    @Override
    public Borrow add(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @Override
    public Borrow update(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @Override
    public List<Borrow> findAllByBookId(Integer bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return borrowRepository.findAllByBook(book);
    }

    @Override
    public List<Borrow> findExpiredBorrows() {
        return borrowRepository.findAllExpiredBorrows();
    }
}
