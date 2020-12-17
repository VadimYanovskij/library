package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Category;
import com.senla.training.library.repository.CategoryRepository;
import com.senla.training.library.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author Vadim Yanovskij
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Finds all categories in the database
     *
     * @return list of Category
     */
    @Override
    public List<Category> findAll() {
        log.info("Listing categories from database");
        List<Category> result = categoryRepository.findAll();
        log.info("Categories listed successfully from database");
        return result;
    }

    /**
     * Finds a category in the database by id.
     * If category not found throw EntityNotFoundException.
     *
     * @return Category
     */
    @Override
    public Category findById(Integer id) {
        log.info("Finding category with id = {} in database", id);
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isPresent()) {
            log.info("Category with id = {} found in database", id);
            return result.get();
        } else {
            throw new EntityNotFoundException("Category not found in database");
        }
    }

    /**
     * Save the category to the database
     *
     * @return saved Category with id
     */
    @Override
    public Category add(Category category) {
        log.info("Creating in database category: {}", category);
        Category result = categoryRepository.save(category);
        log.info("Category created in database successfully with info: \" {}", category);
        return result;
    }


    /**
     * Update the category in the database.
     * If category or parent category not found throw EntityNotFoundException.
     *
     * @return updated User
     */
    @Override
    public Category update(Category category) {
        log.info("Updating in database category: {}", category);
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }
        if (category.getParentId() != null && categoryRepository.findById(category.getParentId()).isEmpty()) {
            throw new EntityNotFoundException("Parent category not found");
        }
        Category result = categoryRepository.save(category);
        log.info("Category updated successfully in database with info: \" {}", category);
        return result;
    }

    /**
     * Delete a category in the database by id.
     */
    @Override
    public void deleteById(Integer id) {
        log.info("Deleting category in database by id = {}", id);
        categoryRepository.deleteById(id);
        log.info("Category with id = {} deleted in database successfully", id);
    }
}
