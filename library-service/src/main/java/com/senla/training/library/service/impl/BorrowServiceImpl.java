package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.entity.Borrow;
import com.senla.training.library.enums.BookStatusName;
import com.senla.training.library.exception.BookAlreadyDeletedException;
import com.senla.training.library.exception.BookOutStockException;
import com.senla.training.library.repository.BookRepository;
import com.senla.training.library.repository.BookStatusRepository;
import com.senla.training.library.repository.BorrowRepository;
import com.senla.training.library.service.BorrowService;
import com.senla.training.library.specifications.BorrowSpecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final BookStatusRepository bookStatusRepository;
    private final BorrowSpecs borrowSpecs;

    public BorrowServiceImpl(BorrowRepository borrowRepository,
                             BookRepository bookRepository,
                             BookStatusRepository bookStatusRepository,
                             BorrowSpecs borrowSpecs) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.bookStatusRepository = bookStatusRepository;
        this.borrowSpecs = borrowSpecs;
    }

    @Override
    public List<Borrow> findAll() {
        log.info("Listing borrows from database");
        List<Borrow> result = borrowRepository.findAll();
        log.info("Borrows listed successfully from database");
        return result;
    }

    @Override
    public Borrow findById(Integer id) {
        log.info("Finding borrow with id = {} in database", id);
        Optional<Borrow> result = borrowRepository.findById(id);
        if (result.isPresent()) {
            log.info("Borrow with id = {} found in database", id);
            return result.get();
        } else {
            throw new EntityNotFoundException("Borrow not found in database");
        }
    }

    @Override
    public Borrow add(Borrow borrow) {
        log.info("Creating in database borrow: {}", borrow);
        Book borrowBook = borrow.getBook();
        if (borrowBook.getBookStatus().getBookStatusName() == BookStatusName.OUT_STOCK) {
            throw new BookOutStockException("Book is out of stock!");
        }
        if (borrowBook.getBookStatus().getBookStatusName() == BookStatusName.DELETED) {
            throw new BookAlreadyDeletedException("This book has DELETED status");
        }
        Borrow result = borrowRepository.save(borrow);
        borrowBook.setBookStatus(bookStatusRepository.findByBookStatusName(
                BookStatusName.OUT_STOCK));
        bookRepository.save(borrowBook);
        log.info("Borrow created in database successfully with info: \" {}", borrow);
        return result;
    }

    @Override
    public Borrow update(Borrow borrow) {
        log.info("Updating in database borrow: {}", borrow);
        if (borrowRepository.findById(borrow.getId()).isPresent()) {
            Borrow result = borrowRepository.save(borrow);
            log.info("Borrow updated successfully with info: \" {}", borrow);
            return result;
        } else {
            throw new EntityNotFoundException("Borrow not found");
        }
    }

    @Override
    public List<Borrow> findAllByBookId(Integer bookId) {
        log.info("Listing borrows history of book with id = {} from database", bookId);
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            List<Borrow> result = borrowRepository.findAllByBook(book.get());
            log.info("Borrows history of book with id = {} listed from database successfully", bookId);
            return result;
        } else {
            throw new EntityNotFoundException("Book not found in database");
        }
    }

    @Override
    public List<Borrow> findExpiredBorrows() {
        log.info("Listing expired borrows from database");
        List<Borrow> result = borrowRepository.findAll(
                Specification
                        .not(borrowSpecs.getBorrowsByBookStatus(
                                bookStatusRepository.findByBookStatusName(BookStatusName.DELETED)))
                        .and(borrowSpecs.getExpiredBorrows())
        );
        log.info("Borrows listed successfully from database");
        return result;
    }
}
