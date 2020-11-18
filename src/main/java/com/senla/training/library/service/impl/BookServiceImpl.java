package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.enums.BookStatus;
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
        return bookRepository.save(book);
    }

    @Override
    public void softDeleteById(Integer id) {
        Book book = findById(id);
        book.setBookStatus(BookStatus.DELETED);
        update(book);
    }

}
