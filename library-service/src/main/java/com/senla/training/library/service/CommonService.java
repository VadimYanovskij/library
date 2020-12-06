package com.senla.training.library.service;

import java.util.List;

public interface CommonService<E> {

    List<E> findAll();

    E findById(Integer id);

    E add(E entity);

    E update(E entity);
}
