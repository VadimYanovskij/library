package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Author;
import com.senla.training.library.repository.AuthorRepository;
import com.senla.training.library.service.AuthorService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found"));
    }

    @Override
    public Author add(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author author) {
        System.out.println("???");
        System.out.println(author.getId());
        if (!authorRepository.findById(author.getId()).isPresent()) {
            System.out.println("Not exist " + author.getId());
            throw new EntityNotFoundException("Author not found");
        }
            return authorRepository.save(author);
    }

    @Override
    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }
}
