package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Publisher;
import com.senla.training.library.repository.PublisherRepository;
import com.senla.training.library.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author Vadim Yanovskij
 */
@Slf4j
@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    /**
     * Finds all publishers in the database
     *
     * @return list of Publisher
     */
    @Override
    public List<Publisher> findAll() {
        log.info("Listing publishers from database");
        List<Publisher> result = publisherRepository.findAll();
        log.info("Publishers listed successfully from database");
        return result;
    }

    /**
     * Finds a publisher in the database by id.
     * If user not found throw EntityNotFoundException.
     *
     * @return Publisher
     */
    @Override
    public Publisher findById(Integer id) {
        log.info("Finding publisher with id = {} in database", id);
        Optional<Publisher> result = publisherRepository.findById(id);
        if (result.isPresent()) {
            log.info("Publisher with id = {} found in database", id);
            return result.get();
        } else {
            throw new EntityNotFoundException("Publisher not found in database");
        }
    }

    /**
     * Save the publisher to the database
     *
     * @return saved Publisher with id
     */
    @Override
    public Publisher add(Publisher publisher) {
        log.info("Creating in database publisher: {}", publisher);
        Publisher result = publisherRepository.save(publisher);
        log.info("Publisher created in database successfully with info: \" {}", publisher);
        return result;
    }

    /**
     * Update the publisher in the database.
     * If publisher not found throw EntityNotFoundException.
     *
     * @return updated User
     */
    @Override
    public Publisher update(Publisher publisher) {
        log.info("Updating in database publisher: {}", publisher);
        if (publisherRepository.findById(publisher.getId()).isPresent()) {
            Publisher result = publisherRepository.save(publisher);
            log.info("Publisher updated successfully in database with info: \" {}", publisher);
            return result;
        } else {
            throw new EntityNotFoundException("Publisher not found");
        }
    }

    /**
     * Delete a publisher in the database by id.
     */
    @Override
    public void deleteById(Integer id) {
        log.info("Deleting publisher in database by id = {}", id);
        publisherRepository.deleteById(id);
        log.info("Publisher with id = {} deleted in database successfully", id);
    }
}
