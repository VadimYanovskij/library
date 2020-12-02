package com.senla.training.library.dto.converter;

import com.senla.training.library.dto.BorrowHistoryDto;
import com.senla.training.library.entity.Borrow;

import java.util.List;

public interface BorrowHistoryConverterDto {
    BorrowHistoryDto entityToDto(Borrow borrow);

    List<BorrowHistoryDto> entitiesToDtos(List<Borrow> borrows);
}
