package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Publisher;
import com.senla.training.library.repository.PublisherRepository;
import com.senla.training.library.service.PublisherService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher findById(Integer id) {
        return publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
    }

    @Override
    public Publisher add(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher update(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public void deleteById(Integer id) {
        publisherRepository.deleteById(id);
    }
}
