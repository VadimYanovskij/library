package com.senla.training.library.controller;

import com.senla.training.library.dto.DtoConverter;
import com.senla.training.library.dto.PublisherDto;
import com.senla.training.library.service.PublisherService;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/publishers")
public class PublisherController {

    private final PublisherService publisherService;
    private final DtoConverter dtoConverter;

    public PublisherController(PublisherService publisherService, DtoConverter dtoConverter) {
        this.publisherService = publisherService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<PublisherDto>> findAll() {
        return new ResponseEntity<>(dtoConverter.publishersToDtos(publisherService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(dtoConverter.publisherToDto(publisherService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublisherDto> add(@Validated(New.class) @RequestBody PublisherDto publisherDto,
                                            BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dtoConverter.publisherToDto(publisherService.add(
                dtoConverter.publisherDtoToEntity(publisherDto))), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PublisherDto> update(@Validated(Exist.class) @RequestBody PublisherDto publisherDto,
                                               BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dtoConverter.publisherToDto(publisherService.add(
                dtoConverter.publisherDtoToEntity(publisherDto))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        publisherService.deleteById(id);
        return new ResponseEntity<>("The Publisher was deleted successfully.", HttpStatus.OK);
    }
}
