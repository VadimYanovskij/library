package com.senla.training.library.controller;

import com.senla.training.library.entity.Book;
import com.senla.training.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
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
    public ResponseEntity<Book> add(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.add(book), HttpStatus.OK);
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
