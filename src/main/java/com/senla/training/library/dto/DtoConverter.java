package com.senla.training.library.dto;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.Publisher;

import java.util.List;

public interface DtoConverter {

    PublisherDto publisherToDto(Publisher publisher);

    Publisher publisherDtoToEntity(PublisherDto publisherDto);

    List<PublisherDto> publishersToDtos(List<Publisher> publishers);

    List<Publisher> publisherDtosToEntities(List<PublisherDto> publisherDtos);

    BookDto bookToDto(Book book);

    Book bookDtoToEntity(BookDto bookDto);
}
