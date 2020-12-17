package com.senla.training.library.repository;

import com.senla.training.library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring JPA Repository for Publisher
 *
 * @author Vadim Yanovskij
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
