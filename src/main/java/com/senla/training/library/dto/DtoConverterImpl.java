package com.senla.training.library.dto;

import com.senla.training.library.entity.Author;
import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.Borrow;
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
    public AuthorDto authorToDto(Author author) {
        return new AuthorDto(
                author.getId(),
                author.getFirstname(),
                author.getLastname()
        );
    }

    @Override
    public Author authorDtoToEntity(AuthorDto authorDto) {
        return new Author(
                authorDto.getId(),
                authorDto.getFirstname(),
                authorDto.getLastname()
        );
    }

    @Override
    public List<AuthorDto> authorsToDtos(List<Author> authors) {
        return authors.stream()
                .map(author -> authorToDto(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> authorDtosToEntities(List<AuthorDto> authorDtos) {
        return authorDtos.stream()
                .map(authorDto -> authorDtoToEntity(authorDto))
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

    @Override
    public List<BookDto> booksToDtos(List<Book> books) {
        return books.stream()
                .map(book -> bookToDto(book))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> bookDtosToEntities(List<BookDto> bookDtos) {
        return bookDtos.stream()
                .map(bookDto -> bookDtoToEntity(bookDto))
                .collect(Collectors.toList());
    }

    @Override
    public BorrowDto borrowToDto(Borrow borrow) {
        return new BorrowDto(
                borrow.getId(),
                borrow.getUser(),
                borrow.getBook(),
                borrow.getBorrowDate(),
                borrow.getRepaymentDate(),
                borrow.getReturnDate()
        );
    }

    @Override
    public Borrow borrowDtoToEntity(BorrowDto borrowDto) {
        return new Borrow(
                borrowDto.getId(),
                borrowDto.getUser(),
                borrowDto.getBook(),
                borrowDto.getBorrowDate(),
                borrowDto.getRepaymentDate(),
                borrowDto.getReturnDate()
        );
    }

    @Override
    public List<BorrowDto> borrowsToDtos(List<Borrow> borrows) {
        return borrows.stream()
                .map(borrow -> borrowToDto(borrow))
                .collect(Collectors.toList());
    }

    @Override
    public List<Borrow> borrowDtosToEntities(List<BorrowDto> borrowDtos) {
        return borrowDtos.stream()
                .map(borrowDto -> borrowDtoToEntity(borrowDto))
                .collect(Collectors.toList());
    }
}
