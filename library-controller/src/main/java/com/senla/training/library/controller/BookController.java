package com.senla.training.library.controller;

import com.senla.training.library.dto.BookDto;
import com.senla.training.library.dto.BookEditDto;
import com.senla.training.library.converter.BookConverterDto;
import com.senla.training.library.converter.BookEditConverterDto;
import com.senla.training.library.service.BookService;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("v1/books")
public class BookController {

    private final BookService bookService;
    private final BookConverterDto bookConverterDto;
    private final BookEditConverterDto bookEditConverterDto;
    private final MessageSource messageSource;

    public BookController(BookService bookService,
                          BookConverterDto bookConverterDto,
                          BookEditConverterDto bookEditConverterDto,
                          MessageSource messageSource) {
        this.bookService = bookService;
        this.bookConverterDto = bookConverterDto;
        this.bookEditConverterDto = bookEditConverterDto;
        this.messageSource = messageSource;
    }

    @GetMapping("/all")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<BookDto>> findAll() {
        log.info("Listing all books");
        ResponseEntity<List<BookDto>> result = new ResponseEntity<>(
                bookConverterDto.entitiesToDtos(
                        bookService.findAll()
                ),
                HttpStatus.OK
        );
        log.info("All books listed successfully");
        return result;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> findAllNotDeletedBooks() {
        log.info("Listing books");
        ResponseEntity<List<BookDto>> result = new ResponseEntity<>(
                bookConverterDto.entitiesToDtos(
                        bookService.findAllNotDeletedBooks()
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
                bookConverterDto.entityToDto(
                        bookService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("Book with id = {} found", id);
        return result;
    }

    @GetMapping("/all/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<BookDto> findByIdWithDeleted(@PathVariable("id") Integer id) {
        log.info("Finding book (WithDeleted) with id = {}", id);
        ResponseEntity<BookDto> result = new ResponseEntity<>(
                bookConverterDto.entityToDto(
                        bookService.findByIdWithDeleted(id)
                ),
                HttpStatus.OK
        );
        log.info("Book (WithDeleted) with id = {} found", id);
        return result;
    }

    @PostMapping
    public ResponseEntity<BookDto> add(
            @Validated(New.class) @RequestBody BookEditDto bookEditDto,
            BindingResult bindingResult) {
        log.info("Creating book: {}", bookEditDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<BookDto> result = new ResponseEntity<>(
                bookConverterDto.entityToDto(
                        bookService.add(
                                bookEditConverterDto.dtoToEntity(bookEditDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Book created successfully with info: \" {}", bookEditDto);
        return result;
    }

    @PutMapping
    public ResponseEntity<BookDto> update(
            @Validated(Exist.class) @RequestBody BookEditDto bookEditDto,
            BindingResult bindingResult) {
        log.info("Updating book: {}", bookEditDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<BookDto> result = new ResponseEntity<>(
                bookConverterDto.entityToDto(
                        bookService.update(
                                bookEditConverterDto.dtoToEntity(bookEditDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Book updated successfully with info: \" {}", bookEditDto);
        return result;
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> softDeleteById(@PathVariable("id") Integer id,
                                                 @RequestParam(value = "locale",
                                                         required = false) Locale locale) {
        log.info("Deleting book by id = {}", id);
        bookService.softDeleteById(id);
        log.info("Book with id = {} deleted successfully", id);
        return new ResponseEntity<>(messageSource.getMessage(
                "label.DeletedSuccessfully", null, locale),
                HttpStatus.OK);
    }

}
