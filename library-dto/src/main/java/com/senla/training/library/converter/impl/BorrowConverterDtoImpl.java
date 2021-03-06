package com.senla.training.library.converter.impl;

import com.senla.training.library.dto.BorrowDto;
import com.senla.training.library.converter.BorrowConverterDto;
import com.senla.training.library.entity.Borrow;
import com.senla.training.library.service.BookService;
import com.senla.training.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BorrowConverterDtoImpl implements BorrowConverterDto {

    private final UserService userService;
    private final BookService bookService;

    public BorrowConverterDtoImpl(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public BorrowDto entityToDto(Borrow borrow) {
        log.info("Converting borrow with id = {} to borrowDto", borrow.getId());
        BorrowDto result = new BorrowDto(
                borrow.getId(),
                borrow.getUser().getId(),
                borrow.getBook().getId(),
                borrow.getBorrowDate(),
                borrow.getRepaymentDate(),
                borrow.getReturnDate()
        );
        log.info("Borrow converted successfully");
        return result;
    }

    @Override
    public Borrow dtoToEntity(BorrowDto borrowDto) {
        log.info("Converting borrowDto with id = {} to borrow", borrowDto.getId());
        Borrow result = new Borrow();
        result.setId(borrowDto.getId());
        if (borrowDto.getUserId() != null) {
            result.setUser(userService.findById(borrowDto.getUserId()));
        }
        if (borrowDto.getBookId() != null) {
            result.setBook(bookService.findById(borrowDto.getBookId()));
        }
        result.setBorrowDate(borrowDto.getBorrowDate());
        result.setRepaymentDate(borrowDto.getRepaymentDate());
        result.setReturnDate(borrowDto.getReturnDate());
        log.info("BorrowDto converted successfully");
        return result;
    }

    @Override
    public List<BorrowDto> entitiesToDtos(List<Borrow> borrows) {
        log.info("Converting borrows to dtos");
        List<BorrowDto> result = borrows.stream()
                .map(borrow -> entityToDto(borrow))
                .collect(Collectors.toList());
        log.info("Borrows converted successfully");
        return result;
    }

    @Override
    public List<Borrow> dtosToEntities(List<BorrowDto> borrowDtos) {
        log.info("Converting borrowDtos to borrows");
        List<Borrow> result = borrowDtos.stream()
                .map(borrowDto -> dtoToEntity(borrowDto))
                .collect(Collectors.toList());
        log.info("borrowDtos converted successfully");
        return result;
    }
}
