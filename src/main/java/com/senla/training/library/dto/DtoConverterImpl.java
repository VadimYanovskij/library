package com.senla.training.library.dto;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverterImpl implements DtoConverter{
    @Override
    public PublisherDto publisherToDto(Publisher publisher) {
        return new PublisherDto(
                publisher.getId(),
                publisher.getPublisherName(),
                publisher.getPublisherCity()
        );
    }

    @Override
    public Publisher publisherDtoToEntity(PublisherDto publisherDto) {
        return new Publisher(
                publisherDto.getId(),
                publisherDto.getPublisherName(),
                publisherDto.getPublisherCity()
        );
    }

    @Override
    public List<PublisherDto> publishersToDtos(List<Publisher> publishers) {
        return publishers.stream()
                .map(publisher -> publisherToDto(publisher))
                .collect(Collectors.toList());
    }

    @Override
    public List<Publisher> publisherDtosToEntities(List<PublisherDto> publisherDtos) {
        return publisherDtos.stream()
                .map(publisherDto -> publisherDtoToEntity(publisherDto))
                .collect(Collectors.toList());
    }

    @Override
    public BookDto bookToDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getName(),
                book.getAuthors(),
                book.getPublisher(),
                book.getPublicationYear(),
                book.getCategory(),
                book.getBookStatus()
        );
    }

    @Override
    public Book bookDtoToEntity(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getName(),
                bookDto.getAuthors(),
                bookDto.getPublisher(),
                bookDto.getPublicationYear(),
                bookDto.getCategory(),
                bookDto.getBookStatus()
        );
    }
}
