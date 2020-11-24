package library.service.impl;

import com.senla.training.library.entity.Book;
import com.senla.training.library.entity.Borrow;
import library.repository.BookRepository;
import library.repository.BorrowRepository;
import library.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;

    public BorrowServiceImpl(BorrowRepository borrowRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Borrow> findAll() {
        log.info("Listing borrows from database");
        List<Borrow> result = borrowRepository.findAll();
        log.info("Borrows listed successfully from database");
        return result;
    }

    @Override
    public Borrow findById(Integer id) {
        log.info("Finding borrow with id = {} in database", id);
        Optional<Borrow> result = borrowRepository.findById(id);
        if (result.isPresent()) {
            log.info("Borrow with id = {} found in database", id);
            return result.get();
        } else {
            log.error("Borrow with id = {} not found in database", id);
            throw new EntityNotFoundException("Borrow not found in database");
        }
    }

    @Override
    public Borrow add(Borrow borrow) {
        log.info("Creating in database borrow: {}", borrow);
        Borrow result = borrowRepository.save(borrow);
        log.info("Borrow created in database successfully with info: \" {}", borrow);
        return result;
    }

    @Override
    public Borrow update(Borrow borrow) {
        log.info("Updating in database borrow: {}", borrow);
        if (borrowRepository.findById(borrow.getId()).isPresent()) {
            Borrow result = borrowRepository.save(borrow);
            log.info("Borrow updated successfully with info: \" {}", borrow);
            return result;
        } else {
            log.error("Borrow with id = {} not found ", borrow.getId());
            throw new EntityNotFoundException("Borrow not found");
        }
    }

    @Override
    public List<Borrow> findAllByBookId(Integer bookId) {
        log.info("Listing borrows history of book with id = {} from database", bookId);
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            List<Borrow> result = borrowRepository.findAllByBook(book.get());
            log.info("Borrows history of book with id = {} listed from database successfully", bookId);
            return result;
        } else {
            log.error("Book with id = {} not found in database", bookId);
            throw new EntityNotFoundException("Book not found in database");
        }
    }

    @Override
    public List<Borrow> findExpiredBorrows() {
        log.info("Listing expired borrows from database");
        List<Borrow> result = borrowRepository.findAllExpiredBorrows();
        log.info("Borrows listed successfully from database");
        return result;
    }
}
