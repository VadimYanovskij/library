package com.senla.training.library.dto.converter.impl;

import com.senla.training.library.dto.CategoryDto;
import com.senla.training.library.dto.converter.CategoryConverterDto;
import com.senla.training.library.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CategoryConverterDtoImpl implements CategoryConverterDto {
    @Override
    public CategoryDto entityToDto(Category category) {
        log.info("Converting category with id = {} to categoryDto", category.getId());
        CategoryDto result = new CategoryDto(
                category.getId(),
                category.getParentId(),
                category.getCategoryName()
        );
        log.info("Category converted successfully");
        return result;
    }

    @Override
    public Category dtoToEntity(CategoryDto categoryDto) {
        log.info("Converting categoryDto with id = {} to category", categoryDto.getId());
        Category result = new Category(
                categoryDto.getId(),
                categoryDto.getParentId(),
                categoryDto.getCategoryName()
        );
        log.info("CategoryDto converted successfully");
        return result;
    }

    @Override
    public List<CategoryDto> entitiesToDtos(List<Category> categories) {
        log.info("Converting categories to dtos");
        List<CategoryDto> result = categories.stream()
                .map(category -> entityToDto(category))
                .collect(Collectors.toList());
        log.info("Categories converted successfully");
        return result;
    }

    @Override
    public List<Category> dtosToEntities(List<CategoryDto> categoryDtos) {
        log.info("Converting categoryDtos to categories");
        List<Category> result = categoryDtos.stream()
                .map(categoryDto -> dtoToEntity(categoryDto))
                .collect(Collectors.toList());
        log.info("CategoryDtos converted successfully");
        return result;
    }
}
