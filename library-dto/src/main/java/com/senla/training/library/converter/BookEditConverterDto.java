package com.senla.training.library.converter;

import com.senla.training.library.dto.BookEditDto;
import com.senla.training.library.entity.Book;

public interface BookEditConverterDto {

    Book dtoToEntity (BookEditDto bookEditDto);

}
