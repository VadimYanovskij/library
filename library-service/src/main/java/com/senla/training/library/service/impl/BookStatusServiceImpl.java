package com.senla.training.library.service.impl;

import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.enums.BookStatusName;
import com.senla.training.library.repository.BookStatusRepository;
import com.senla.training.library.service.BookStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@Service
public class BookStatusServiceImpl implements BookStatusService {

    private final BookStatusRepository bookStatusRepository;

    public BookStatusServiceImpl(BookStatusRepository bookStatusRepository) {
        this.bookStatusRepository = bookStatusRepository;
    }

    @Override
    public BookStatus findById(Integer id) {
        log.info("Finding book status with id = {} in database", id);
        Optional<BookStatus> result = bookStatusRepository.findById(id);
        if (result.isPresent()) {
            log.info("Book status with id = {} found in database", id);
            return result.get();
        } else {
            throw new EntityNotFoundException("Book status not found in database");
        }
    }
}
