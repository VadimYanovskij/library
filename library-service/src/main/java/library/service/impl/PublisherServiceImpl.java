package library.service.impl;

import com.senla.training.library.entity.Publisher;
import library.repository.PublisherRepository;
import library.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> findAll() {
        log.info("Listing publishers from database");
        List<Publisher> result = publisherRepository.findAll();
        log.info("Publishers listed successfully from database");
        return result;
    }

    @Override
    public Publisher findById(Integer id) {
        log.info("Finding publisher with id = {} in database", id);
        Optional<Publisher> result = publisherRepository.findById(id);
        if (result.isPresent()) {
            log.info("Publisher with id = {} found in database", id);
            return result.get();
        } else {
            log.error("Publisher with id = {} not found in database", id);
            throw new EntityNotFoundException("Publisher not found in database");
        }
    }

    @Override
    public Publisher add(Publisher publisher) {
        log.info("Creating in database publisher: {}", publisher);
        Publisher result = publisherRepository.save(publisher);
        log.info("Publisher created in database successfully with info: \" {}", publisher);
        return result;
    }

    @Override
    public Publisher update(Publisher publisher) {
        log.info("Updating in database publisher: {}", publisher);
        if (publisherRepository.findById(publisher.getId()).isPresent()) {
            Publisher result = publisherRepository.save(publisher);
            log.info("Publisher updated successfully in database with info: \" {}", publisher);
            return result;
        } else {
            log.error("Publisher with id = {} not found ", publisher.getId());
            throw new EntityNotFoundException("Publisher not found");
        }
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting publisher in database by id = {}", id);
        publisherRepository.deleteById(id);
        log.info("Publisher with id = {} deleted in database successfully", id);
    }
}
