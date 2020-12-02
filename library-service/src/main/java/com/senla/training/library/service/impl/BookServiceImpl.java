package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.enums.BookStatusName;
import com.senla.training.library.exception.BookAlreadyDeletedException;
import com.senla.training.library.repository.BookRepository;
import com.senla.training.library.repository.BookStatusRepository;
import com.senla.training.library.service.BookService;
import com.senla.training.library.specifications.BookSpecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookStatusRepository bookStatusRepository;
    private final BookSpecs bookSpecs;

    public BookServiceImpl(BookRepository bookRepository,
                           BookStatusRepository bookStatusRepository,
                           BookSpecs bookSpecs) {
        this.bookRepository = bookRepository;
        this.bookStatusRepository = bookStatusRepository;
        this.bookSpecs = bookSpecs;
    }

    @Override
    public List<Book> findAll() {
        log.info("Listing all books from database");
        List<Book> result = bookRepository.findAll();
        log.info("All books listed successfully from database");
        return result;
    }

    @Override
    public List<Book> findAllNotDeletedBooks() {
        log.info("Listing books without deleted from database");
        List<Book> result = bookRepository.findAll(
                Specification
                        .not(bookSpecs.getBooksByBookStatusId(bookStatusRepository.findByBookStatusName(
                                BookStatusName.DELETED)))
        );
        log.info("Books without deleted listed successfully from database");
        return result;
    }

    @Override
    public Book findById(Integer id) {
        log.info("Finding book with id = {} in database", id);
        Optional<Book> result = bookRepository.findById(id);
        if (result.isPresent()) {
            log.info("Book with id = {} found in database", id);
            if (result.get().getBookStatus().getBookStatusName() == BookStatusName.DELETED) {
                throw new BookAlreadyDeletedException("This book has DELETED status");
            }
            return result.get();
        } else {
            throw new EntityNotFoundException("Book not found in database");
        }
    }

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

    @Override
    public Book add(Book book) {
        log.info("Creating in database book: {}", book);
        Book result = bookRepository.save(book);
        log.info("Book created in database successfully with info: \" {}", book);
        return result;
    }

    @Override
    public Book update(Book book) {
        log.info("Updating in database book: {}", book);
        Optional<Book> bookForUpdate = bookRepository.findById(book.getId());
        if (bookForUpdate.isPresent()) {
            Book updatedBook = bookForUpdate.get();
            if (book.getName() != null) {
                updatedBook.setName(book.getName());
            }
            if (book.getAuthors() != null){
                updatedBook.setAuthors(book.getAuthors());
            }
            if (book.getPublisher() != null){
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

    @Override
    public void softDeleteById(Integer id) {
        log.info("Deleting book in database by id = {}", id);
        Book book = findById(id);
        if (book.getBookStatus().getBookStatusName() == BookStatusName.DELETED) {
            throw new BookAlreadyDeletedException("Book has already been deleted!");
        }
        book.setBookStatus(bookStatusRepository.findByBookStatusName(
                BookStatusName.DELETED));
        bookRepository.save(book);
        log.info("Book with id = {} deleted in database successfully", id);
    }

}
