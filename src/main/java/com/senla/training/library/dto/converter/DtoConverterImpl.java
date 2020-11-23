package com.senla.training.library.dto.converter;

import com.senla.training.library.dto.*;
import com.senla.training.library.entity.*;
import com.senla.training.library.service.BookService;
import com.senla.training.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DtoConverterImpl implements DtoConverter {

    private final UserService userService;
    private final BookService bookService;

    public DtoConverterImpl(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public PublisherDto publisherToDto(Publisher publisher) {
        log.info("Converting publisher with id = {} to dto", publisher.getId());
        PublisherDto result = new PublisherDto(
                publisher.getId(),
                publisher.getPublisherName(),
                publisher.getPublisherCity()
        );
        log.info("Publisher converted successfully");
        return result;
    }

    @Override
    public Publisher publisherDtoToEntity(PublisherDto publisherDto) {
        log.info("Converting publisherDto with id = {} to publisher", publisherDto.getId());
        Publisher result = new Publisher(
                publisherDto.getId(),
                publisherDto.getPublisherName(),
                publisherDto.getPublisherCity()
        );
        log.info("PublisherDto converted successfully");
        return result;
    }

    @Override
    public List<PublisherDto> publishersToDtos(List<Publisher> publishers) {
        log.info("Converting publishers to dtos");
        List<PublisherDto> result = publishers.stream()
                .map(publisher -> publisherToDto(publisher))
                .collect(Collectors.toList());
        log.info("Publishers converted successfully");
        return result;
    }

    @Override
    public List<Publisher> publisherDtosToEntities(List<PublisherDto> publisherDtos) {
        log.info("Converting publisherDtos to publishers");
        List<Publisher> result = publisherDtos.stream()
                .map(publisherDto -> publisherDtoToEntity(publisherDto))
                .collect(Collectors.toList());
        log.info("PublisherDtos converted successfully");
        return result;
    }

    @Override
    public AuthorDto authorToDto(Author author) {
        log.info("Converting author with id = {} to dto", author.getId());
        AuthorDto result = new AuthorDto(
                author.getId(),
                author.getFirstname(),
                author.getLastname()
        );
        log.info("Author converted successfully");
        return result;
    }

    @Override
    public Author authorDtoToEntity(AuthorDto authorDto) {
        log.info("Converting authorDto with id = {} to author", authorDto.getId());
        Author result = new Author(
                authorDto.getId(),
                authorDto.getFirstname(),
                authorDto.getLastname()
        );
        log.info("AuthorDto converted successfully");
        return result;
    }

    @Override
    public List<AuthorDto> authorsToDtos(List<Author> authors) {
        log.info("Converting authors to dtos");
        List<AuthorDto> result = authors.stream()
                .map(author -> authorToDto(author))
                .collect(Collectors.toList());
        log.info("Authors converted successfully");
        return result;
    }

    @Override
    public List<Author> authorDtosToEntities(List<AuthorDto> authorDtos) {
        log.info("Converting authorDtos to authors");
        List<Author> result = authorDtos.stream()
                .map(authorDto -> authorDtoToEntity(authorDto))
                .collect(Collectors.toList());
        log.info("AuthorsDtos converted successfully");
        return result;
    }

    @Override
    public BookDto bookToDto(Book book) {
        log.info("Converting book with id = {} to dto", book.getId());
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
    public Book bookDtoToEntity(BookDto bookDto) {
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
    public List<BookDto> booksToDtos(List<Book> books) {
        log.info("Converting books to dtos");
        List<BookDto> result = books.stream()
                .map(book -> bookToDto(book))
                .collect(Collectors.toList());
        log.info("Books converted successfully");
        return result;
    }

    @Override
    public List<Book> bookDtosToEntities(List<BookDto> bookDtos) {
        log.info("Converting bookDtos to books");
        List<Book> result = bookDtos.stream()
                .map(bookDto -> bookDtoToEntity(bookDto))
                .collect(Collectors.toList());
        log.info("BookDtos converted successfully");
        return result;
    }

    @Override
    public BorrowDto borrowToDto(Borrow borrow) {
        log.info("Converting borrow with id = {} to dto", borrow.getId());
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
    public Borrow borrowDtoToEntity(BorrowDto borrowDto) {
        log.info("Converting borrowDto with id = {} to borrow", borrowDto.getId());
        Borrow result = new Borrow(
                borrowDto.getId(),
                userService.findById(borrowDto.getUserId()),
                bookService.findById(borrowDto.getBookId()),
                borrowDto.getBorrowDate(),
                borrowDto.getRepaymentDate(),
                borrowDto.getReturnDate()
        );
        log.info("BorrowDto converted successfully");
        return result;
    }

    @Override
    public List<BorrowDto> borrowsToDtos(List<Borrow> borrows) {
        log.info("Converting borrows to dtos");
        List<BorrowDto> result = borrows.stream()
                .map(borrow -> borrowToDto(borrow))
                .collect(Collectors.toList());
        log.info("Borrows converted successfully");
        return result;
    }

    @Override
    public List<Borrow> borrowDtosToEntities(List<BorrowDto> borrowDtos) {
        log.info("Converting borrowDtos to borrows");
        List<Borrow> result = borrowDtos.stream()
                .map(borrowDto -> borrowDtoToEntity(borrowDto))
                .collect(Collectors.toList());
        log.info("borrowDtos converted successfully");
        return result;
    }

    @Override
    public UserDto userToDto(User user) {
        log.info("Converting user with id = {} to dto", user.getId());
        UserDto result = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getBirthday(),
                user.getRoles()
        );
        log.info("User converted successfully");
        return result;
    }

    @Override
    public User userDtoToEntity(UserDto userDto) {
        log.info("Converting userDto with id = {} to user", userDto.getId());
        User result = new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getBirthday(),
                userDto.getRoles()
        );
        log.info("UserDto converted successfully");
        return result;
    }

    @Override
    public List<UserDto> usersToDtos(List<User> users) {
        log.info("Converting users to dtos");
        List<UserDto> result = users.stream()
                .map(user -> userToDto(user))
                .collect(Collectors.toList());
        log.info("Users converted successfully");
        return result;
    }

    @Override
    public List<User> userDtosToEntities(List<UserDto> userDtos) {
        log.info("Converting userDtos to users");
        List<User> result = userDtos.stream()
                .map(userDto -> userDtoToEntity(userDto))
                .collect(Collectors.toList());
        log.info("UserDtos converted successfully");
        return result;
    }

    @Override
    public CategoryDto categoryToDto(Category category) {
        log.info("Converting category with id = {} to dto", category.getId());
        CategoryDto result = new CategoryDto(
                category.getId(),
                category.getParentId(),
                category.getCategoryName()
        );
        log.info("Category converted successfully");
        return result;
    }

    @Override
    public Category categoryDtoToEntity(CategoryDto categoryDto) {
        log.info("Converting categoryDto with id = {} to category", categoryDto.getId());
        Category result = new Category(
                categoryDto.getId(),
                categoryDto.getParentId(),
                categoryDto.getCategoryName()
        );
        log.info("CategoryDto converted successfully");
        return result;
    }

    @Override
    public List<CategoryDto> categoriesToDtos(List<Category> categories) {
        log.info("Converting categories to dtos");
        List<CategoryDto> result = categories.stream()
                .map(category -> categoryToDto(category))
                .collect(Collectors.toList());
        log.info("Categories converted successfully");
        return result;
    }

    @Override
    public List<Category> categoryDtosToEntities(List<CategoryDto> categoryDtos) {
        log.info("Converting categoryDtos to categories");
        List<Category> result = categoryDtos.stream()
                .map(categoryDto -> categoryDtoToEntity(categoryDto))
                .collect(Collectors.toList());
        log.info("CategoryDtos converted successfully");
        return result;
    }

    @Override
    public BorrowHistoryDto borrowToBorrowHistoryDto(Borrow borrow) {
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
                borrow.getBook().getBookStatus().name(),
                borrow.getBorrowDate(),
                borrow.getRepaymentDate(),
                borrow.getReturnDate()
        );
        log.info("Borrow to borrowHistoryDto converted successfully");
        return result;
    }

    @Override
    public List<BorrowHistoryDto> borrowsToBorrowHistoryDtos(List<Borrow> borrows) {
        log.info("Converting borrows to borrowHistoryDtos");
        List<BorrowHistoryDto> result = borrows.stream()
                .map(borrow -> borrowToBorrowHistoryDto(borrow))
                .collect(Collectors.toList());
        log.info("Borrows to borrowHistoryDtos converted successfully");
        return result;
    }
}
