package com.senla.training.library.controller;

import com.senla.training.library.entity.Borrow;
import com.senla.training.library.service.BorrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/borrows")
public class BorrowController {
    
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public ResponseEntity<List<Borrow>> findAll() {
        return new ResponseEntity<>(borrowService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrow> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(borrowService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Borrow> add(@RequestBody Borrow borrow) {
        return new ResponseEntity<>(borrowService.add(borrow), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Borrow borrow) {
        borrowService.update(borrow);
        return new ResponseEntity<>("The Borrow was updated successfully", HttpStatus.OK);
    }

    @GetMapping("/history/{bookId}")
    public ResponseEntity<List<Borrow>> findAllByBookId(@PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(borrowService.findAllByBookId(bookId), HttpStatus.OK);
    }

    @GetMapping("/expired")
    public ResponseEntity<List<Borrow>> findExpiredBorrows() {
        return new ResponseEntity<>(borrowService.findExpiredBorrows(), HttpStatus.OK);
    }
}
