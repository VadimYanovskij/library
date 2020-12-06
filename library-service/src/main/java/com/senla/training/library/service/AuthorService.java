package com.senla.training.library.service;

import com.senla.training.library.entity.Author;

public interface AuthorService extends CommonService<Author> {

    void deleteById(Integer id);
}
