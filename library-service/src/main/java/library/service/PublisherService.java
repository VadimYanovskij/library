package library.service;

import com.senla.training.library.entity.Publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> findAll();

    Publisher findById(Integer id);

    Publisher add(Publisher publisher);

    Publisher update(Publisher publisher);

    void deleteById(Integer id);

}
