package com.senla.training.library.repository;

import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.enums.BookStatusName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring JPA Repository for BookStatus
 *
 * @author Vadim Yanovskij
 */
public interface BookStatusRepository extends JpaRepository<BookStatus, Integer> {
    Optional<BookStatus> findByBookStatusName(BookStatusName bookStatusName);
}
