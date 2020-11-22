package com.senla.training.library.controller;

import com.senla.training.library.dto.BorrowDto;
import com.senla.training.library.dto.DtoConverter;
import com.senla.training.library.service.BorrowService;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(
                dtoConverter.borrowsToDtos(
                        borrowService.findAll()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(
                dtoConverter.borrowToDto(
                        borrowService.findById(id)
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<BorrowDto> add(@Validated(New.class) @RequestBody BorrowDto borrowDto,
                                         BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                dtoConverter.borrowToDto(
                        borrowService.add(
                                dtoConverter.borrowDtoToEntity(borrowDto)
                        )
                ),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<BorrowDto> update(@Validated(Exist.class) @RequestBody BorrowDto borrowDto,
                                            BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                dtoConverter.borrowToDto(
                        borrowService.update(
                                dtoConverter.borrowDtoToEntity(borrowDto)
                        )
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/history/{bookId}")
    public ResponseEntity<List<BorrowDto>> findAllByBookId(@PathVariable("bookId") Integer bookId) {
        return new ResponseEntity<>(
                dtoConverter.borrowsToDtos(
                        borrowService.findAllByBookId(bookId)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/expired")
    public ResponseEntity<List<BorrowDto>> findExpiredBorrows() {
        return new ResponseEntity<>(
                dtoConverter.borrowsToDtos(borrowService.findExpiredBorrows()
                ),
                HttpStatus.OK
        );
    }
}
