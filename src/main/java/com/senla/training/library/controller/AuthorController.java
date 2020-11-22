package com.senla.training.library.controller;

import com.senla.training.library.dto.AuthorDto;
import com.senla.training.library.dto.DtoConverter;
import com.senla.training.library.service.AuthorService;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final DtoConverter dtoConverter;

    public AuthorController(AuthorService authorService, DtoConverter dtoConverter) {
        this.authorService = authorService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    ResponseEntity<List<AuthorDto>> findAll() {
        return new ResponseEntity<>(
                dtoConverter.authorsToDtos(
                        authorService.findAll()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<AuthorDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(
                dtoConverter.authorToDto(
                        authorService.findById(id)
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    ResponseEntity<AuthorDto> add(@Validated(New.class) @RequestBody AuthorDto authorDto,
                                  BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                dtoConverter.authorToDto(
                        authorService.add(
                                dtoConverter.authorDtoToEntity(authorDto)
                        )
                ),
                HttpStatus.OK
        );
    }

    @PutMapping
    ResponseEntity<AuthorDto> update(@Validated(Exist.class) @RequestBody AuthorDto authorDto,
                                     BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                dtoConverter.authorToDto(
                        authorService.update(
                                dtoConverter.authorDtoToEntity(authorDto)
                        )
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        authorService.deleteById(id);
        return new ResponseEntity<>("The Author was deleted successfully.", HttpStatus.OK);
    }
}
