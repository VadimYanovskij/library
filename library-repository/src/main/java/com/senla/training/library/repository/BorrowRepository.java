package com.senla.training.library.repository;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer>, JpaSpecificationExecutor<Borrow> {

    List<Borrow> findAllByBook(Book book);

}
