package com.senla.training.library.controller;


import com.senla.training.library.converter.AuthorConverterDto;
import com.senla.training.library.dto.AuthorDto;
import com.senla.training.library.service.AuthorService;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("v1/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorConverterDto authorConverterDto;
    private final MessageSource messageSource;

    public AuthorController(AuthorService authorService,
                            AuthorConverterDto authorConverterDto,
                            MessageSource messageSource) {
        this.authorService = authorService;
        this.authorConverterDto = authorConverterDto;
        this.messageSource = messageSource;
    }

    @GetMapping
    ResponseEntity<List<AuthorDto>> findAll() {
        log.info("Listing authors");
        ResponseEntity<List<AuthorDto>> result = new ResponseEntity<>(
                authorConverterDto.entitiesToDtos(
                        authorService.findAll()
                ),
                HttpStatus.OK
        );
        log.info("Authors listed successfully");
        return result;
    }

    @GetMapping("/{id}")
    ResponseEntity<AuthorDto> findById(@PathVariable("id") Integer id) {
        log.info("Finding author with id = {}", id);
        ResponseEntity<AuthorDto> result = new ResponseEntity<>(
                authorConverterDto.entityToDto(
                        authorService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("Author with id = {} found", id);
        return result;
    }

    @PostMapping
    ResponseEntity<AuthorDto> add(
            @Validated(New.class) @RequestBody AuthorDto authorDto,
            BindingResult bindingResult) {
        log.info("Creating author: {}", authorDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<AuthorDto> result = new ResponseEntity<>(
                authorConverterDto.entityToDto(
                        authorService.add(
                                authorConverterDto.dtoToEntity(authorDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Author created successfully with info: \" {}", authorDto);
        return result;
    }

    @PutMapping
    ResponseEntity<AuthorDto> update(
            @Validated(Exist.class) @RequestBody AuthorDto authorDto,
            BindingResult bindingResult) {
        log.info("Updating author: {}", authorDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<AuthorDto> result = new ResponseEntity<>(
                authorConverterDto.entityToDto(
                        authorService.update(
                                authorConverterDto.dtoToEntity(authorDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Author updated successfully with info: \" {}", authorDto);
        return result;
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<String> deleteById(@PathVariable("id") Integer id,
                                      @RequestParam("locale") Locale locale) {
        log.info("Deleting author by id = {}", id);
        authorService.deleteById(id);
        log.info("Author with id = {} deleted successfully", id);
        return new ResponseEntity<>(messageSource.getMessage(
                "label.DeletedSuccessfully", null, locale),
                HttpStatus.OK);
    }
}
