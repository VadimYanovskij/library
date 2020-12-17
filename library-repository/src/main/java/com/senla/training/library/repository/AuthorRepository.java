package com.senla.training.library.repository;

import com.senla.training.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring JPA Repository for Token
 *
 * @author Author Yanovskij
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
