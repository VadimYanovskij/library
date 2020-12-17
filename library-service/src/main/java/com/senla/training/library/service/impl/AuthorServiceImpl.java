package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Author;
import com.senla.training.library.repository.AuthorRepository;
import com.senla.training.library.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author Vadim Yanovskij
 */
@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Finds all authors in the database
     *
     * @return list of Author
     */
    @Override
    public List<Author> findAll() {
        log.info("Listing authors from database");
        List<Author> result = authorRepository.findAll();
        log.info("Authors listed successfully from database");
        return result;
    }

    /**
     * Finds an author in the database by id.
     * If author not found throw EntityNotFoundException.
     *
     * @return Author
     */
    @Override
    public Author findById(Integer id) {
        log.info("Finding author with id = {} in database", id);
        Optional<Author> result = authorRepository.findById(id);
        if (result.isPresent()) {
            log.info("Author with id = {} found in database", id);
            return result.get();
        } else {
            throw new EntityNotFoundException("Author not found in database");
        }
    }

    /**
     * Save the author to the database
     *
     * @return saved Author with id
     */
    @Override
    public Author add(Author author) {
        log.info("Creating in database author: {}", author);
        Author result = authorRepository.save(author);
        log.info("Author created successfully with info: \" {}", author);
        return result;
    }

    /**
     * Update the author in the database.
     * If author or parent category not found throw EntityNotFoundException.
     *
     * @return updated Author
     */
    @Override
    public Author update(Author author) {
        log.info("Updating in database author: {}", author);
        if (authorRepository.findById(author.getId()).isEmpty()) {
            throw new EntityNotFoundException("Author not found in database");
        }
        Author result = authorRepository.save(author);
        log.info("Author updated successfully in database with info: \" {}", author);
        return result;
    }

    /**
     * Delete an author in the database by id.
     */
    @Override
    public void deleteById(Integer id) {
        log.info("Deleting author in database by id = {}", id);
        authorRepository.deleteById(id);
        log.info("Author with id = {} deleted in database successfully", id);
    }
}
