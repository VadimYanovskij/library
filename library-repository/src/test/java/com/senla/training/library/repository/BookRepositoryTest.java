package com.senla.training.library.repository;

import com.senla.training.library.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = LibraryAppRepositoryTestConfig.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void givenSetOfBooks_whenFindAll_thenReturnAllBooks() {
        Book firstBook = new Book();
        firstBook.setName("First");
        Book secondBook = new Book();
        secondBook.setName("Second");
        Book thirdBook = new Book();
        thirdBook.setName("Third");

        entityManager.persist(firstBook);
        entityManager.persist(secondBook);
        entityManager.persist(thirdBook);
        entityManager.flush();

        List<Book> allBooks = bookRepository.findAll();

        assertThat(allBooks).hasSize(3).extracting(Book::getName)
                .containsOnly(firstBook.getName(), secondBook.getName(),
                        thirdBook.getName());
    }

    @Test
    public void whenFindById_thenReturnBook() {
        Book book = new Book();
        book.setName("test");
        entityManager.persistAndFlush(book);

        Book fromDb = bookRepository.findById(book.getId()).orElse(null);
        assert fromDb != null;
        assertThat(fromDb.getName()).isEqualTo(book.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Book fromDb = bookRepository.findById(-1).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenSaveNewBook_thenBookSaveInDbWithGeneratedId() {
        Book book = new Book();
        book.setName("Test");
        Book fromDb = bookRepository.save(book);
        assertThat(fromDb.getId()).isNotNull();
        assertThat(bookRepository.findById(fromDb.getId()).get())
                .isEqualTo(fromDb);
    }

    @Test
    public void whenSaveExistBook_thenBookUpdateInDb() {
        Book book = new Book();
        book.setName("Test");
        Book fromDb = bookRepository.save(book);
        assertThat(book.getName()).isEqualTo(fromDb.getName());
        fromDb.setName("NewName");
        Book fromDbAfterRename = bookRepository.save(fromDb);
        assertThat(fromDb.getId()).isEqualTo(fromDbAfterRename.getId());
        assertThat(fromDb.getName()).isEqualTo(fromDbAfterRename.getName());
    }
}
