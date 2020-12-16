package com.senla.training.library.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.senla.training.library.dto.BorrowDto;
import com.senla.training.library.dto.BorrowHistoryDto;
import com.senla.training.library.converter.BorrowConverterDto;
import com.senla.training.library.converter.BorrowHistoryConverterDto;
import com.senla.training.library.service.BorrowService;
import com.senla.training.library.transfer.UserDetails;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
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
@RequestMapping("v1/borrows")
public class BorrowController {

    private final BorrowService borrowService;
    private final BorrowConverterDto borrowConverterDto;
    private final BorrowHistoryConverterDto borrowHistoryConverterDto;

    public BorrowController(BorrowService borrowService,
                            BorrowConverterDto borrowConverterDto,
                            BorrowHistoryConverterDto borrowHistoryConverterDto) {
        this.borrowService = borrowService;
        this.borrowConverterDto = borrowConverterDto;
        this.borrowHistoryConverterDto = borrowHistoryConverterDto;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    @JsonView({UserDetails.class})
    public ResponseEntity<List<BorrowDto>> findAll() {
        log.info("Listing borrows");
        ResponseEntity<List<BorrowDto>> result = new ResponseEntity<>(
                borrowConverterDto.entitiesToDtos(
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
                borrowConverterDto.entityToDto(
                        borrowService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("Borrow with id = {} found", id);
        return result;
    }

    @PostMapping
    public ResponseEntity<BorrowDto> add(
            @Validated(New.class) @RequestBody BorrowDto borrowDto,
            BindingResult bindingResult) {
        log.info("Creating borrow: {}", borrowDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<BorrowDto> result = new ResponseEntity<>(
                borrowConverterDto.entityToDto(
                        borrowService.add(
                                borrowConverterDto.dtoToEntity(borrowDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Borrow created successfully with info: \" {}", borrowDto);
        return result;
    }

    @PutMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<BorrowDto> update(
            @Validated(Exist.class) @RequestBody BorrowDto borrowDto,
            BindingResult bindingResult) {
        log.info("Updating borrow: {}", borrowDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<BorrowDto> result = new ResponseEntity<>(
                borrowConverterDto.entityToDto(
                        borrowService.update(
                                borrowConverterDto.dtoToEntity(borrowDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Borrow updated successfully with info: \" {}", borrowDto);
        return result;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/history/{bookId}")
    public ResponseEntity<List<BorrowHistoryDto>> borrowHistoryByBookId(
            @PathVariable("bookId") Integer bookId) {
        log.info("Listing borrows history of book with id = {}", bookId);
        ResponseEntity<List<BorrowHistoryDto>> result = new ResponseEntity<>(
                borrowHistoryConverterDto.entitiesToDtos(
                        borrowService.findAllByBookId(bookId)
                ),
                HttpStatus.OK
        );
        log.info("Borrows history of book with id = {} listed successfully", bookId);
        return result;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/expired")
    public ResponseEntity<List<BorrowDto>> findExpiredBorrows() {
        log.info("Listing expired borrows");
        ResponseEntity<List<BorrowDto>> result = new ResponseEntity<>(
                borrowConverterDto.entitiesToDtos(borrowService.findExpiredBorrows()
                ),
                HttpStatus.OK
        );
        log.info("Borrows listed successfully");
        return result;
    }
}
