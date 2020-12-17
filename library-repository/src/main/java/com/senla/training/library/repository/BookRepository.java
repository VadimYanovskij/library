package com.senla.training.library.repository;

import com.senla.training.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring JPA Repository for Book
 *
 * @author Vadim Yanovskij
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}