package com.senla.training.library.service;

import com.senla.training.library.entity.BookStatus;
import com.senla.training.library.enums.BookStatusName;

public interface BookStatusService {

    BookStatus findById(Integer id);

    BookStatus findByBookStatusName(BookStatusName bookStatusName);

}
