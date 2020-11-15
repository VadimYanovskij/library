package com.senla.training.library.service;

import com.senla.training.library.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Integer id);
    Book add(Book book);
    Book update(Book book);
    void deleteById(Integer id);
}
