package com.senla.training.library.service;

import com.senla.training.library.entity.Publisher;

public interface PublisherService extends CommonService<Publisher> {

    void deleteById(Integer id);

}
