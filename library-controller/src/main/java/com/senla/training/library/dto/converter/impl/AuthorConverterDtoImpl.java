package com.senla.training.library.dto.converter.impl;

import com.senla.training.library.dto.AuthorDto;
import com.senla.training.library.dto.converter.AuthorConverterDto;
import com.senla.training.library.entity.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AuthorConverterDtoImpl implements AuthorConverterDto {
    @Override
    public AuthorDto entityToDto(Author author) {
        log.info("Converting author with id = {} to authorDto", author.getId());
        AuthorDto result = new AuthorDto(
                author.getId(),
                author.getFirstname(),
                author.getLastname()
        );
        log.info("Author converted successfully");
        return result;
    }

    @Override
    public Author dtoToEntity(AuthorDto authorDto) {
        log.info("Converting authorDto with id = {} to author", authorDto.getId());
        Author result = new Author(
                authorDto.getId(),
                authorDto.getFirstname(),
                authorDto.getLastname()
        );
        log.info("AuthorDto converted successfully");
        return result;
    }

    @Override
    public List<AuthorDto> entitiesToDtos(List<Author> authors) {
        log.info("Converting authors to dtos");
        List<AuthorDto> result = authors.stream()
                .map(author -> entityToDto(author))
                .collect(Collectors.toList());
        log.info("Authors converted successfully");
        return result;
    }

    @Override
    public List<Author> dtosToEntities(List<AuthorDto> authorDtos) {
        log.info("Converting authorDtos to authors");
        List<Author> result = authorDtos.stream()
                .map(authorDto -> dtoToEntity(authorDto))
                .collect(Collectors.toList());
        log.info("AuthorsDtos converted successfully");
        return result;
    }
}
