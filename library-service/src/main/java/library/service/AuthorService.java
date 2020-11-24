package library.service;

import com.senla.training.library.entity.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(Integer id);

    Author add(Author author);

    Author update(Author author);

    void deleteById(Integer id);
}
