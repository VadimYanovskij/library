package com.senla.training.library.controller;

import com.senla.training.library.dto.BorrowDto;
import com.senla.training.library.dto.converter.DtoConverter;
import com.senla.training.library.service.BorrowService;
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
@RequestMapping("v1/borrows")
public class BorrowController {

    private final BorrowService borrowService;
    private final DtoConverter dtoConverter;

    public BorrowController(BorrowService borrowService, DtoConverter dtoConverter) {
        this.borrowService = borrowService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<BorrowDto>> findAll() {
        log.info("Listing borrows");
        ResponseEntity<List<BorrowDto>> result = new ResponseEntity<>(
                dtoConverter.borrowsToDtos(
                        borrowService.findAll()
                ),
                HttpStatus.OK
        );
        log.info("Borrows listed successfully");
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowDto> findById(@PathVariable("id") Integer id) {
        log.info("Finding borrow with id = {}", id);
        ResponseEntity<BorrowDto> result = new ResponseEntity<>(
                dtoConverter.borrowToDto(
                        borrowService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("Borrow with id = {} found", id);
        return result;
    }

    @PostMapping
    public ResponseEntity<BorrowDto> add(@Validated(New.class) @RequestBody BorrowDto borrowDto,
                                         BindingResult bindingResult) {
        log.info("Creating borrow: {}", borrowDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<BorrowDto> result = new ResponseEntity<>(
                dtoConverter.borrowToDto(
                        borrowService.add(
                                dtoConverter.borrowDtoToEntity(borrowDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Borrow created successfully with info: \" {}", borrowDto);
        return result;
    }

    @PutMapping
    public ResponseEntity<BorrowDto> update(@Validated(Exist.class) @RequestBody BorrowDto borrowDto,
                                            BindingResult bindingResult) {
        log.info("Updating borrow: {}", borrowDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<BorrowDto> result = new ResponseEntity<>(
                dtoConverter.borrowToDto(
                        borrowService.update(
                                dtoConverter.borrowDtoToEntity(borrowDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Borrow updated successfully with info: \" {}", borrowDto);
        return result;
    }

    @GetMapping("/history/{bookId}")
    public ResponseEntity<List<BorrowDto>> findAllByBookId(@PathVariable("bookId") Integer bookId) {
        log.info("Listing borrows history of book with id = {}", bookId);
        ResponseEntity<List<BorrowDto>> result = new ResponseEntity<>(
                dtoConverter.borrowsToDtos(
                        borrowService.findAllByBookId(bookId)
                ),
                HttpStatus.OK
        );
        log.info("Borrows history of book with id = {} listed successfully", bookId);
        return result;
    }

    @GetMapping("/expired")
    public ResponseEntity<List<BorrowDto>> findExpiredBorrows() {
        log.info("Listing expired borrows");
        ResponseEntity<List<BorrowDto>> result = new ResponseEntity<>(
                dtoConverter.borrowsToDtos(borrowService.findExpiredBorrows()
                ),
                HttpStatus.OK
        );
        log.info("Borrows listed successfully");
        return result;
    }
}
