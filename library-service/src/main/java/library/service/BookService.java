package library.service;

import com.senla.training.library.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    List<Book> findAllNotDeletedBooks();

    Book findById(Integer id);

    Book findByIdWithDeleted(Integer id);

    Book add(Book book);

    Book update(Book book);

    void softDeleteById(Integer id);
}
