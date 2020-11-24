package library.service;

import com.senla.training.library.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Integer id);

    Category add(Category category);

    Category update(Category category);

    void deleteById(Integer id);
}
