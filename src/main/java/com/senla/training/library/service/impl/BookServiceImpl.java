package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.Category;
import com.senla.training.library.enums.BookStatus;
import com.senla.training.library.exception.EntityAlreadyDeleted;
import com.senla.training.library.repository.BookRepository;
import com.senla.training.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        log.info("Listing books from database");
        List<Book> result = bookRepository.findAll();
        log.info("Books listed successfully from database");
        return result;
    }

    @Override
    public Book findById(Integer id) {
        log.info("Finding book with id = {} in database", id);
        Optional<Book> result = bookRepository.findById(id);
        if (result.isPresent()) {
            log.info("Book with id = {} found in database", id);
            return result.get();
        } else {
            log.error("Book with id = {} not found in database", id);
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
        if (bookRepository.findById(book.getId()).isPresent()) {
            Book result = bookRepository.save(book);
            log.info("Book updated successfully in database with info: \" {}", book);
            return result;
        } else {
            log.error("Book with id = {} not found ", book.getId());
            throw new EntityNotFoundException("Book not found in database");
        }
    }

    @Override
    public void softDeleteById(Integer id) {
        log.info("Deleting book in database by id = {}", id);
        Book book = findById(id);
        if (book.getBookStatus() == BookStatus.DELETED) {
            log.error("Book has already been deleted!");
            throw new EntityAlreadyDeleted("Book has already been deleted!");
        }
        book.setBookStatus(BookStatus.DELETED);
        bookRepository.save(book);
        log.info("Book with id = {} deleted in database successfully", id);
    }

}
