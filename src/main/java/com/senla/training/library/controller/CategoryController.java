package com.senla.training.library.controller;

import com.senla.training.library.dto.CategoryDto;
import com.senla.training.library.dto.converter.DtoConverter;
import com.senla.training.library.service.CategoryService;
import com.senla.training.library.transfer.New;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final DtoConverter dtoConverter;

    public CategoryController(CategoryService categoryService, DtoConverter dtoConverter) {
        this.categoryService = categoryService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        log.info("Listing categories");
        ResponseEntity<List<CategoryDto>> result = new ResponseEntity<>(
                dtoConverter.categoriesToDtos(
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
                dtoConverter.categoryToDto(
                        categoryService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("Category with id = {} found", id);
        return result;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> add(@Validated(New.class) @RequestBody CategoryDto categoryDto,
                                           BindingResult bindingResult) {
        log.info("Creating category: {}", categoryDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<CategoryDto> result = new ResponseEntity<>(
                dtoConverter.categoryToDto(
                        categoryService.add(
                                dtoConverter.categoryDtoToEntity(categoryDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Category created successfully with info: \" {}", categoryDto);
        return result;
    }
    @PutMapping
    public ResponseEntity<CategoryDto> update(@Validated(New.class) @RequestBody CategoryDto categoryDto,
                                              BindingResult bindingResult){
        log.info("Updating category: {}", categoryDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<CategoryDto> result = new ResponseEntity<>(
                dtoConverter.categoryToDto(
                        categoryService.update(
                                dtoConverter.categoryDtoToEntity(categoryDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Category updated successfully with info: \" {}", categoryDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        log.info("Deleting category by id = {}", id);
        categoryService.deleteById(id);
        log.info("Category with id = {} deleted successfully", id);
        return new ResponseEntity<>("The Category deleted successfully.", HttpStatus.OK);
    }
}
