package com.senla.training.library.service;

import com.senla.training.library.entity.Category;

public interface CategoryService extends CommonService<Category> {

    void deleteById(Integer id);
}
