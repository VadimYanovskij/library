package com.senla.training.library.controller;

import com.senla.training.library.dto.BookDto;
import com.senla.training.library.dto.converter.DtoConverter;
import com.senla.training.library.service.BookService;
import com.senla.training.library.transfer.Exist;
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
@RequestMapping("v1/books")
public class BookController {

    private final BookService bookService;
    private final DtoConverter dtoConverter;

    public BookController(BookService bookService, DtoConverter dtoConverter) {
        this.bookService = bookService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        log.info("Listing books");
        ResponseEntity<List<BookDto>> result = new ResponseEntity<>(
                dtoConverter.booksToDtos(
                        bookService.findAll()
                ),
                HttpStatus.OK
        );
        log.info("Books listed successfully");
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") Integer id) {
        log.info("Finding book with id = {}", id);
        ResponseEntity<BookDto> result = new ResponseEntity<>(
                dtoConverter.bookToDto(
                        bookService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("Book with id = {} found", id);
        return result;
    }

    @PostMapping
    public ResponseEntity<BookDto> add(@Validated(New.class) @RequestBody BookDto bookDto,
                                       BindingResult bindingResult) {
        log.info("Creating book: {}", bookDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<BookDto> result = new ResponseEntity<>(
                dtoConverter.bookToDto(
                        bookService.add(
                                dtoConverter.bookDtoToEntity(bookDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Book created successfully with info: \" {}", bookDto);
        return result;
    }

    @PutMapping
    public ResponseEntity<BookDto> update(@Validated(Exist.class) @RequestBody BookDto bookDto,
                                          BindingResult bindingResult) {
        log.info("Updating book: {}", bookDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<BookDto> result = new ResponseEntity<>(
                dtoConverter.bookToDto(
                        bookService.update(
                                dtoConverter.bookDtoToEntity(bookDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Book updated successfully with info: \" {}", bookDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteById(@PathVariable("id") Integer id) {
        log.info("Deleting book by id = {}", id);
        bookService.softDeleteById(id);
        log.info("Book with id = {} deleted successfully", id);
        return new ResponseEntity<>("The Book deleted successfully", HttpStatus.OK);
    }

}
