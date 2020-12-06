package com.senla.training.library.converter.impl;

import com.senla.training.library.dto.BookDto;
import com.senla.training.library.converter.BookConverterDto;
import com.senla.training.library.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BookConverterDtoImpl implements BookConverterDto {
    @Override
    public BookDto entityToDto(Book book) {
        log.info("Converting book with id = {} to bookDto", book.getId());
        BookDto result = new BookDto(
                book.getId(),
                book.getName(),
                book.getAuthors(),
                book.getPublisher(),
                book.getPublicationYear(),
                book.getCategory(),
                book.getBookStatus()
        );
        log.info("Book converted successfully");
        return result;
    }

    @Override
    public Book dtoToEntity(BookDto bookDto) {
        log.info("Converting bookDto with id = {} to book", bookDto.getId());
        Book result = new Book(
                bookDto.getId(),
                bookDto.getName(),
                bookDto.getAuthors(),
                bookDto.getPublisher(),
                bookDto.getPublicationYear(),
                bookDto.getCategory(),
                bookDto.getBookStatus()
        );
        log.info("BookDto converted successfully");
        return result;
    }

    @Override
    public List<BookDto> entitiesToDtos(List<Book> books) {
        log.info("Converting books to dtos");
        List<BookDto> result = books.stream()
                .map(book -> entityToDto(book))
                .collect(Collectors.toList());
        log.info("Books converted successfully");
        return result;
    }

    @Override
    public List<Book> dtosToEntities(List<BookDto> bookDtos) {
        log.info("Converting bookDtos to books");
        List<Book> result = bookDtos.stream()
                .map(bookDto -> dtoToEntity(bookDto))
                .collect(Collectors.toList());
        log.info("BookDtos converted successfully");
        return result;
    }
}
