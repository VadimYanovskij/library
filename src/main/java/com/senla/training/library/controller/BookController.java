package com.senla.training.library.controller;

import com.senla.training.library.dto.BookDto;
import com.senla.training.library.dto.DtoConverter;
import com.senla.training.library.entity.Book;
import com.senla.training.library.service.BookService;
import com.senla.training.library.transfer.New;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Book>> findAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> add(@Validated(New.class) @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.add(dtoConverter.bookDtoToEntity(bookDto)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Book book) {
        bookService.update(book);
        return new ResponseEntity<>("The Book was updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteById(@PathVariable("id") Integer id) {
        bookService.softDeleteById(id);
        return new ResponseEntity<>("The Book was deleted successfully", HttpStatus.OK);
    }

}
