package com.senla.training.library.repository;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring JPA Repository for Borrow
 *
 * @author Vadim Yanovskij
 */
@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

    List<Borrow> findAllByBook(Book book);

}
