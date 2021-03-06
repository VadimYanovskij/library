package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.enums.BookStatusName;
import com.senla.training.library.exception.BookAlreadyDeletedException;
import com.senla.training.library.repository.BookRepository;
import com.senla.training.library.service.BookService;
import com.senla.training.library.service.BookStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Vadim Yanovskij
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookStatusService bookStatusService;

    public BookServiceImpl(BookRepository bookRepository,
                           BookStatusService bookStatusService) {
        this.bookRepository = bookRepository;
        this.bookStatusService = bookStatusService;
    }

    /**
     * Finds all books in the database
     *
     * @return list of Book
     */
    @Override
    public List<Book> findAll() {
        log.info("Listing all books from database");
        List<Book> result = bookRepository.findAll();
        log.info("All books listed successfully from database");
        return result;
    }

    /**
     * Finds all not deleted books in the database
     *
     * @return list of Book
     */
    @Override
    public List<Book> findAllNotDeletedBooks() {
        log.info("Listing books without deleted from database");
        List<Book> result = bookRepository.findAll().stream().
                filter(book -> book.getBookStatus() != bookStatusService.
                        findByBookStatusName(BookStatusName.DELETED))
                .collect(Collectors.toList());
        log.info("Books without deleted listed successfully from database");
        return result;
    }

    /**
     * Finds a not deleted book in the database by id.
     * If book not found throw EntityNotFoundException.
     * If book has DELETED status throw BookAlreadyDeletedException.
     *
     * @return Book
     */
    @Override
    public Book findById(Integer id) {
        log.info("Finding book with id = {} in database", id);
        Optional<Book> result = bookRepository.findById(id);
        if (result.isPresent()) {
            log.info("Book with id = {} found in database", id);
            if (result.get().getBookStatus().getBookStatusName() ==
                    BookStatusName.DELETED) {
                throw new BookAlreadyDeletedException("This book has DELETED status");
            }
            return result.get();
        } else {
            throw new EntityNotFoundException("Book not found in database");
        }
    }

    /**
     * Finds a book in the database by id.
     * If book not found throw EntityNotFoundException.
     *
     * @return Book
     */
    @Override
    public Book findByIdWithDeleted(Integer id) {
        log.info("Finding book (WithDeleted) with id = {} in database", id);
        Optional<Book> result = bookRepository.findById(id);
        if (result.isPresent()) {
            log.info("Book (WithDeleted) with id = {} found in database", id);
            return result.get();
        } else {
            throw new EntityNotFoundException("Book not found in database");
        }
    }

    /**
     * Save the book to the database.
     *
     * @return saved Book with id
     */
    @Override
    public Book add(Book book) {
        log.info("Creating in database book: {}", book);
        Book result = bookRepository.save(book);
        log.info("Book created in database successfully with info: \" {}", book);
        return result;
    }

    /**
     * Update the book in the database.
     * If book not found throw EntityNotFoundException.
     *
     * @return updated Book
     */
    @Override
    public Book update(Book book) {
        log.info("Updating in database book: {}", book);
        Optional<Book> bookForUpdate = bookRepository.findById(book.getId());
        if (bookForUpdate.isPresent()) {
            Book updatedBook = bookForUpdate.get();
            if (book.getName() != null) {
                updatedBook.setName(book.getName());
            }
            if (book.getAuthors() != null) {
                updatedBook.setAuthors(book.getAuthors());
            }
            if (book.getPublisher() != null) {
                updatedBook.setPublisher(book.getPublisher());
            }
            if (book.getPublicationYear() != null) {
                updatedBook.setPublicationYear(book.getPublicationYear());
            }
            if (book.getCategory() != null) {
                updatedBook.setCategory(book.getCategory());
            }
            if (book.getBookStatus() != null) {
                updatedBook.setBookStatus(book.getBookStatus());
            }
            Book result = bookRepository.save(updatedBook);
            log.info("Book updated successfully in database with info: \" {}", book);
            return result;
        } else {
            throw new EntityNotFoundException("Book not found in database");
        }
    }

    /**
     * Finds a book by id and change status to DELETED and save in the database.
     */
    @Override
    public void softDeleteById(Integer id) {
        log.info("Deleting book in database by id = {}", id);
        Book book = findById(id);
        book.setBookStatus(bookStatusService.findByBookStatusName(
                BookStatusName.DELETED));
        bookRepository.save(book);
        log.info("Book with id = {} deleted in database successfully", id);
    }

}