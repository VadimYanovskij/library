package com.senla.training.library.service;

import com.senla.training.library.entity.Book;

import java.util.List;

public interface BookService extends CommonService<Book> {

    List<Book> findAllNotDeletedBooks();

    Book findByIdWithDeleted(Integer id);

    void softDeleteById(Integer id);
}
