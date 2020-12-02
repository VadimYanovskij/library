package com.senla.training.library.controller;

import com.senla.training.library.dto.CategoryDto;
import com.senla.training.library.dto.converter.CategoryConverterDto;

import com.senla.training.library.transfer.New;
import com.senla.training.library.service.CategoryService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryConverterDto categoryConverterDto;

    public CategoryController(CategoryService categoryService,
                              CategoryConverterDto categoryConverterDto) {
        this.categoryService = categoryService;
        this.categoryConverterDto = categoryConverterDto;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        log.info("Listing categories");
        ResponseEntity<List<CategoryDto>> result = new ResponseEntity<>(
                categoryConverterDto.entitiesToDtos(
                        categoryService.findAll()
                ),
                HttpStatus.OK
        );
        log.info("Categories listed successfully");
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("id") Integer id) {
        log.info("Finding category with id = {}", id);
        ResponseEntity<CategoryDto> result = new ResponseEntity<>(
                categoryConverterDto.entityToDto(
                        categoryService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("Category with id = {} found", id);
        return result;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> add(
            @Validated(New.class) @RequestBody CategoryDto categoryDto,
            BindingResult bindingResult) {
        log.info("Creating category: {}", categoryDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<CategoryDto> result = new ResponseEntity<>(
                categoryConverterDto.entityToDto(
                        categoryService.add(
                                categoryConverterDto.dtoToEntity(categoryDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Category created successfully with info: \" {}", categoryDto);
        return result;
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(
            @Validated(New.class) @RequestBody CategoryDto categoryDto,
            BindingResult bindingResult) {
        log.info("Updating category: {}", categoryDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<CategoryDto> result = new ResponseEntity<>(
                categoryConverterDto.entityToDto(
                        categoryService.update(
                                categoryConverterDto.dtoToEntity(categoryDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Category updated successfully with info: \" {}", categoryDto);
        return result;
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        log.info("Deleting category by id = {}", id);
        categoryService.deleteById(id);
        log.info("Category with id = {} deleted successfully", id);
        return new ResponseEntity<>("The Category deleted successfully.", HttpStatus.OK);
    }
}
