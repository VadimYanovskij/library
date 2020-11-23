package com.senla.training.library.dto.converter;

import com.senla.training.library.dto.*;
import com.senla.training.library.entity.*;

import java.util.List;

public interface DtoConverter {

    PublisherDto publisherToDto(Publisher publisher);

    Publisher publisherDtoToEntity(PublisherDto publisherDto);

    List<PublisherDto> publishersToDtos(List<Publisher> publishers);

    List<Publisher> publisherDtosToEntities(List<PublisherDto> publisherDtos);


    AuthorDto authorToDto(Author author);

    Author authorDtoToEntity(AuthorDto authorDto);

    List<AuthorDto> authorsToDtos(List<Author> authors);

    List<Author> authorDtosToEntities(List<AuthorDto> authorDtos);


    BookDto bookToDto(Book book);

    Book bookDtoToEntity(BookDto bookDto);

    List<BookDto> booksToDtos(List<Book> books);

    List<Book> bookDtosToEntities(List<BookDto> bookDtos);


    BorrowDto borrowToDto(Borrow borrow);

    Borrow borrowDtoToEntity(BorrowDto borrowDto);

    List<BorrowDto> borrowsToDtos(List<Borrow> borrows);

    List<Borrow> borrowDtosToEntities(List<BorrowDto> borrowDtos);


    UserDto userToDto(User user);

    User userDtoToEntity(UserDto userDto);

    List<UserDto> usersToDtos(List<User> users);

    List<User> userDtosToEntities(List<UserDto> userDtos);


    CategoryDto categoryToDto(Category category);

    Category categoryDtoToEntity(CategoryDto categoryDto);

    List<CategoryDto> categoriesToDtos(List<Category> categories);

    List<Category> categoryDtosToEntities(List<CategoryDto> categoryDtos);


    BorrowHistoryDto borrowToBorrowHistoryDto(Borrow borrow);

    List<BorrowHistoryDto> borrowsToBorrowHistoryDtos(List<Borrow> borrows);
}
