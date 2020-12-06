package com.senla.training.library.converter.impl;

import com.senla.training.library.dto.BorrowHistoryDto;
import com.senla.training.library.converter.BorrowHistoryConverterDto;
import com.senla.training.library.entity.Borrow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BorrowHistoryConverterDtoImpl implements BorrowHistoryConverterDto {
    @Override
    public BorrowHistoryDto entityToDto(Borrow borrow) {
        log.info("Converting borrow with id = {} to borrowHistoryDto", borrow.getId());
        BorrowHistoryDto result = new BorrowHistoryDto(
                borrow.getId(),
                borrow.getUser().getId(),
                borrow.getUser().getUsername(),
                borrow.getBook().getId(),
                borrow.getBook().getName(),
                borrow.getBook().getAuthors(),
                borrow.getBook().getPublisher().getId(),
                borrow.getBook().getPublisher().getPublisherName(),
                borrow.getBook().getPublicationYear(),
                borrow.getBook().getCategory().getId(),
                borrow.getBook().getCategory().getCategoryName(),
                borrow.getBook().getBookStatus().getId(),
                borrow.getBook().getBookStatus().getBookStatusName().name(),
                borrow.getBorrowDate(),
                borrow.getRepaymentDate(),
                borrow.getReturnDate()
        );
        log.info("Borrow to borrowHistoryDto converted successfully");
        return result;
    }

    @Override
    public List<BorrowHistoryDto> entitiesToDtos(List<Borrow> borrows) {
        log.info("Converting borrows to borrowHistoryDtos");
        List<BorrowHistoryDto> result = borrows.stream()
                .map(borrow -> entityToDto(borrow))
                .collect(Collectors.toList());
        log.info("Borrows to borrowHistoryDtos converted successfully");
        return result;
    }
}
