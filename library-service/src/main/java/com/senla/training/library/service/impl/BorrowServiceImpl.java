package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.entity.Borrow;
import com.senla.training.library.enums.BookStatusName;
import com.senla.training.library.exception.BookAlreadyDeletedException;
import com.senla.training.library.exception.BookOutStockException;
import com.senla.training.library.repository.BookRepository;
import com.senla.training.library.repository.BorrowRepository;
import com.senla.training.library.service.BookStatusService;
import com.senla.training.library.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Vadim Yanovskij
 */
@Slf4j
@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final BookStatusService bookStatusService;

    public BorrowServiceImpl(BorrowRepository borrowRepository,
                             BookRepository bookRepository,
                             BookStatusService bookStatusService) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.bookStatusService = bookStatusService;
    }

    /**
     * Finds all borrows in the database
     *
     * @return list of Borrow
     */
    @Override
    public List<Borrow> findAll() {
        log.info("Listing borrows from database");
        List<Borrow> result = borrowRepository.findAll();
        log.info("Borrows listed successfully from database");
        return result;
    }

    /**
     * Finds a borrow in the database by id.
     * If borrow not found throw EntityNotFoundException.
     *
     * @return Borrow
     */
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

    /**
     * Save the borrow to the database.
     * If borrowed book has OUT_STOCK status throw BookOutStockException.
     * If borrowed book has DELETED status throw BookAlreadyDeletedException.
     *
     * @return saved Borrow with id
     */
    @Override
    @Transactional
    public Borrow add(Borrow borrow) {
        log.info("Creating in database borrow: {}", borrow);
        Book borrowBook = borrow.getBook();
        if (borrowBook.getBookStatus().getBookStatusName() ==
                BookStatusName.OUT_STOCK) {
            throw new BookOutStockException("Book is out of stock!");
        }
        if (borrowBook.getBookStatus().getBookStatusName() ==
                BookStatusName.DELETED) {
            throw new BookAlreadyDeletedException("This book has DELETED status");
        }
        Borrow result = borrowRepository.save(borrow);
        borrowBook.setBookStatus(bookStatusService.findByBookStatusName(
                BookStatusName.OUT_STOCK));
        bookRepository.save(borrowBook);
        log.info("Borrow created in database successfully with info: \" {}", borrow);
        return result;
    }

    /**
     * Update the borrow in the database.
     * If borrow not found throw EntityNotFoundException.
     *
     * @return updated Borrow
     */
    @Override
    public Borrow update(Borrow borrow) {
        log.info("Updating in database borrow: {}", borrow);
        Optional<Borrow> borrowForUpdate = borrowRepository.findById(borrow.getId());
        if (borrowForUpdate.isPresent()) {
            Borrow updatedBorrow = borrowForUpdate.get();
            if (borrow.getUser() != null) {
                updatedBorrow.setUser(borrow.getUser());
            }
            if (borrow.getBook() != null) {
                updatedBorrow.setBook(borrow.getBook());
            }
            if (borrow.getBorrowDate() != null) {
                updatedBorrow.setBorrowDate(borrow.getBorrowDate());
            }
            if (borrow.getRepaymentDate() != null) {
                updatedBorrow.setRepaymentDate(borrow.getRepaymentDate());
            }
            if (borrow.getReturnDate() != null) {
                updatedBorrow.setReturnDate(borrow.getReturnDate());
            }
            Borrow result = borrowRepository.save(updatedBorrow);
            log.info("Borrow updated successfully with info: \" {}", borrow);
            return result;
        } else {
            throw new EntityNotFoundException("Borrow not found");
        }
    }

    /**
     * Finds all borrows in the database by book id.
     * If book not found throw EntityNotFoundException.
     *
     * @return list of Borrow
     */
    @Override
    public List<Borrow> findAllByBookId(Integer bookId) {
        log.info("Listing borrows history of book with id = {} from database",
                bookId);
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            List<Borrow> result = borrowRepository.findAllByBook(book.get());
            log.info("Borrows history of book with id = {} " +
                    "listed from database successfully", bookId);
            return result;
        } else {
            throw new EntityNotFoundException("Book not found in database");
        }
    }

    /**
     * Finds expired borrows in the database.
     *
     * @return list of Borrow
     */
    @Override
    public List<Borrow> findExpiredBorrows() {
        log.info("Listing expired borrows from database");
        BookStatus bookStatusDeleted = bookStatusService.findByBookStatusName(
                BookStatusName.DELETED);
        List<Borrow> result = new ArrayList<>();
        for (Borrow borrow : borrowRepository.findAll()) {
            if (borrow.getBook().getBookStatus() != bookStatusDeleted) {
                if (borrow.getReturnDate() == null) {
                    if (LocalDate.now().isAfter(borrow.getRepaymentDate())) {
                        result.add(borrow);
                    }
                } else {
                    if (borrow.getReturnDate().isAfter(borrow.getRepaymentDate())) {
                        result.add(borrow);
                    }
                }
            }
        }
        log.info("Expired borrows listed successfully from database");
        return result;
    }
}
