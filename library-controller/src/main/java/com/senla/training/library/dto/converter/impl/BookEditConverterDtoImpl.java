package com.senla.training.library.dto.converter.impl;

import com.senla.training.library.dto.BookEditDto;
import com.senla.training.library.dto.converter.BookEditConverterDto;
import com.senla.training.library.entity.Book;
import com.senla.training.library.service.AuthorService;
import com.senla.training.library.service.BookStatusService;
import com.senla.training.library.service.CategoryService;
import com.senla.training.library.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
public class BookEditConverterDtoImpl implements BookEditConverterDto {

    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final BookStatusService bookStatusService;

    public BookEditConverterDtoImpl(AuthorService authorService,
                                    PublisherService publisherService,
                                    CategoryService categoryService,
                                    BookStatusService bookStatusService) {
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
        this.bookStatusService = bookStatusService;
    }


    @Override
    public Book dtoToEntity(BookEditDto bookEditDto) {
        log.info("Converting bookEditDto {} to book", bookEditDto);
        Book result = new Book();
        result.setId(bookEditDto.getId());
        result.setName(bookEditDto.getName());
        if (bookEditDto.getAuthorIds() != null) {
            result.setAuthors(
                    bookEditDto.getAuthorIds().stream()
                            .map(id -> authorService.findById(id))
                            .collect(Collectors.toSet())
            );
        }
        if (bookEditDto.getPublisherId() != null) {
            result.setPublisher(publisherService.findById(bookEditDto.getPublisherId()));
        }
        result.setPublicationYear(bookEditDto.getPublicationYear());
        if (bookEditDto.getCategoryId() != null) {
            result.setCategory(categoryService.findById(bookEditDto.getCategoryId()));
        }
        if (bookEditDto.getBookStatusId() != null) {
            result.setBookStatus(bookStatusService.findById(bookEditDto.getBookStatusId()));
        }
        log.info("BookEditDto converted successfully to book: {}", result);
        return result;
    }
}
