package com.senla.training.library.repository;

import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.enums.BookStatusName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookStatusRepository extends JpaRepository<BookStatus, Integer> {
    Optional<BookStatus> findByBookStatusName(BookStatusName bookStatusName);
}
