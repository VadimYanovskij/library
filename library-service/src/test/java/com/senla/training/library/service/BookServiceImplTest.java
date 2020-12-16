package com.senla.training.library.service;

import com.senla.training.library.entity.*;
import com.senla.training.library.enums.BookStatusName;
import com.senla.training.library.exception.BookAlreadyDeletedException;
import com.senla.training.library.repository.BookRepository;
import com.senla.training.library.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookStatusService bookStatusService;

    @InjectMocks
    private BookServiceImpl bookService;

    Integer wrongId;
    BookStatus inStock;
    BookStatus outStock;
    BookStatus deleted;
    Book inStockBook;
    Book outStockBook;
    Book deletedBook;
    List<Book> allBooks;

    @Before
    public void setUp() {
        wrongId = -1;

        inStock = new BookStatus(1, BookStatusName.IN_STOCK);
        outStock = new BookStatus(2, BookStatusName.OUT_STOCK);
        deleted = new BookStatus(3, BookStatusName.DELETED);

        inStockBook = new Book();
        inStockBook.setName("In Stock Book");
        inStockBook.setId(1);
        inStockBook.setBookStatus(inStock);
        outStockBook = new Book();
        outStockBook.setName("Out Stock Book");
        outStockBook.setId(2);
        outStockBook.setBookStatus(outStock);
        deletedBook = new Book();
        deletedBook.setName("Deleted Book");
        deletedBook.setId(3);
        deletedBook.setBookStatus(deleted);

        allBooks = Arrays.asList(inStockBook, outStockBook, deletedBook);

        when(bookRepository.findById(inStockBook.getId()))
                .thenReturn(Optional.of(inStockBook));
        when(bookRepository.findById(deletedBook.getId()))
                .thenReturn(Optional.of(deletedBook));
        when(bookRepository.findAll()).thenReturn(allBooks);
        when(bookRepository.findById(wrongId)).thenReturn(Optional.empty());
        when(bookStatusService.findByBookStatusName(BookStatusName.DELETED))
                .thenReturn(deleted);
    }

    @Test
    public void given3Books_whenFindAll_thenReturn3Records() {
        List<Book> books = bookService.findAll();

        assertThat(books).hasSize(allBooks.size()).extracting(Book::getName)
                .contains(inStockBook.getName(),
                        outStockBook.getName(),
                        deletedBook.getName());

        verifyFindAllBooksIsCalledOnce();
    }

    @Test
    public void given3Books_whenFindAllNotDeletedBooks_thenReturn2Records() {
        List<Book> notDeletedBooks = bookService.findAllNotDeletedBooks();

        assertThat(notDeletedBooks).hasSize(2).extracting(Book::getName)
                .contains(inStockBook.getName(), outStockBook.getName());

        verifyFindAllBooksIsCalledOnce();
    }

    @Test
    public void whenFindByIdWithValidId_thenBookShouldBeFound() {
        Book fromDb = bookService.findById(inStockBook.getId());
        assertThat(fromDb.getName()).isEqualTo(inStockBook.getName());

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenFindByIdWithInValidId_thenEntityNotFoundException() {
        assertThatThrownBy(() -> bookService.findById(wrongId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Book not found in database");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenFindByIdWithValidIdAndBookStatusIsDELETED_thenBookAlreadyDeletedException() {
        assertThatThrownBy(() -> bookService.findById(deletedBook.getId()))
                .isInstanceOf(BookAlreadyDeletedException.class)
                .hasMessageContaining("This book has DELETED status");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenFindByIdWithDeletedWithValidId_thenBookShouldBeFound() {
        Book fromDb = bookService.findByIdWithDeleted(deletedBook.getId());
        assertThat(fromDb.getName()).isEqualTo(deletedBook.getName());

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenFindByIdWithDeletedWithInValidId_thenEntityNotFoundException() {
        assertThatThrownBy(() -> bookService.findByIdWithDeleted(wrongId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Book not found in database");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenAddNewBook_thenBookSaveInDbWithGeneratedId() {
        Book newBook = new Book();
        newBook.setName("New book");

        Book newBookAfterSave = new Book();
        newBookAfterSave.setId(4);
        newBook.setName("New book");

        when(bookRepository.save(newBook)).thenReturn(newBookAfterSave);

        assertThat(bookService.add(newBook).getId())
                .isEqualTo(newBookAfterSave.getId());

        verifySaveIsCalledOnce();
    }

    @Test
    public void whenUpdateBook_thenBookSaveInDb() {
        Author kuk = new Author(1, "Глен", "Кук");
        Publisher ast = new Publisher(1, "АСТ", "Москва");
        Category fantasy = new Category(2, 1, "Фентези");

        Book bookForUpdate = new Book(5, "Чёрный отряд", Set.of(kuk), ast,
                1997, fantasy, inStock);

        when(bookRepository.findById(bookForUpdate.getId()))
                .thenReturn(Optional.of(bookForUpdate));

        Author cixin = new Author(2, "Лю", "Цысинь");
        Publisher fanzon = new Publisher(1, "Fanzon", "Москва");
        Category scienceFiction = new Category(3, 1, "Фантастика");

        bookForUpdate.setName("Задача трех тел");
        bookForUpdate.setAuthors(Set.of(cixin));
        bookForUpdate.setPublisher(fanzon);
        bookForUpdate.setPublicationYear(2017);
        bookForUpdate.setCategory(scienceFiction);
        bookForUpdate.setBookStatus(outStock);

        when(bookRepository.save(bookForUpdate)).thenReturn(bookForUpdate);

        assertThat(bookService.update(bookForUpdate)).isEqualTo(bookForUpdate);

        verifySaveIsCalledOnce();
    }

    @Test
    public void whenSoftDeleteById_thenChangeBookStatusToDELETED() {
        Book bookForDelete = new Book();
        bookForDelete.setId(6);
        bookForDelete.setBookStatus(inStock);

        when(bookRepository.findById(bookForDelete.getId()))
                .thenReturn(Optional.of(bookForDelete));

        bookService.softDeleteById(bookForDelete.getId());

        assertThat(bookForDelete.getBookStatus()).isEqualTo(deleted);

        verifySaveIsCalledOnce();
    }

    @Test
    public void whenSoftDeleteByIdAlreadyDeletedBook_thenBookAlreadyDeletedException() {
        assertThatThrownBy(() -> bookService.softDeleteById(deletedBook.getId()))
                .isInstanceOf(BookAlreadyDeletedException.class)
                .hasMessageContaining("This book has DELETED status");

        verify(bookRepository, VerificationModeFactory
                .times(0)).save(any());
        reset(bookRepository);
    }


    private void verifyFindAllBooksIsCalledOnce() {
        verify(bookRepository, VerificationModeFactory
                .times(1)).findAll();
        reset(bookRepository);
    }

    private void verifyFindByIdIsCalledOnce() {
        verify(bookRepository, VerificationModeFactory
                .times(1)).findById(Mockito.anyInt());
        reset(bookRepository);
    }

    private void verifySaveIsCalledOnce() {
        verify(bookRepository, VerificationModeFactory
                .times(1)).save(any());
        reset(bookRepository);
    }
}
