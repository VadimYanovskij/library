package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Category;
import com.senla.training.library.repository.CategoryRepository;
import com.senla.training.library.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        log.info("Listing categories from database");
        List<Category> result = categoryRepository.findAll();
        log.info("Categories listed successfully from database");
        return result;
    }

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

    @Override
    public Category add(Category category) {
        log.info("Creating in database category: {}", category);
        Category result = categoryRepository.save(category);
        log.info("Category created in database successfully with info: \" {}", category);
        return result;
    }

    @Override
    public Category update(Category category) {
        log.info("Updating in database category: {}", category);
        if (!categoryRepository.findById(category.getId()).isPresent()) {
            throw new EntityNotFoundException("Category not found");
        }
        if (!categoryRepository.findById(category.getParentId()).isPresent()) {
            throw new EntityNotFoundException("Parent category not found");
        }
        Category result = categoryRepository.save(category);
        log.info("Category updated successfully in database with info: \" {}", category);
        return result;
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting category in database by id = {}", id);
        categoryRepository.deleteById(id);
        log.info("Category with id = {} deleted in database successfully", id);
    }
}
