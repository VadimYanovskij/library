package com.senla.training.library.repository;

import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.enums.BookStatusName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStatusRepository extends JpaRepository<BookStatus, Integer> {
    BookStatus findByBookStatusName(BookStatusName bookStatusName);
}
