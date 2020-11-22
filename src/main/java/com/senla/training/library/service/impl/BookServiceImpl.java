package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.enums.BookStatus;
import com.senla.training.library.exception.EntityAlreadyDeleted;
import com.senla.training.library.repository.BookRepository;
import com.senla.training.library.service.BookService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    @Override
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        if (!bookRepository.findById(book.getId()).isPresent()) {
            System.out.println("Not exist " + book.getId());
            throw new EntityNotFoundException("Book not found");
        }
        return bookRepository.save(book);
    }

    @Override
    public void softDeleteById(Integer id) {
        Book book = findById(id);
        if (book == null) {
            throw new EntityNotFoundException("Book not found");
        }
        if (book.getBookStatus() == BookStatus.DELETED) {
            throw new EntityAlreadyDeleted("Book already deleted!");
        }
        book.setBookStatus(BookStatus.DELETED);
        bookRepository.save(book);
    }

}
