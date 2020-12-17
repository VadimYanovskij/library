package com.senla.training.library.repository;

import com.senla.training.library.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring JPA Repository for Category
 *
 * @author Vadim Yanovskij
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
