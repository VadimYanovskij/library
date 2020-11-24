package com.senla.training.library.controller;

import com.senla.training.library.dto.PublisherDto;

import com.senla.training.library.dto.converter.DtoConverter;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import com.senla.training.library.service.PublisherService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("Listing publishers");
        ResponseEntity<List<PublisherDto>> result = new ResponseEntity<>(
                dtoConverter.publishersToDtos(
                        publisherService.findAll()
                ),
                HttpStatus.OK
        );
        log.info("Publishers listed successfully");
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> findById(@PathVariable("id") Integer id) {
        log.info("Finding publisher with id = {}", id);
        ResponseEntity<PublisherDto> result = new ResponseEntity<>(
                dtoConverter.publisherToDto(
                        publisherService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("Publisher with id = {} found", id);
        return result;
    }

    @PostMapping
    public ResponseEntity<PublisherDto> add(@Validated(New.class) @RequestBody PublisherDto publisherDto,
                                            BindingResult bindingResult) {
        log.info("Creating publisher: {}", publisherDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<PublisherDto> result = new ResponseEntity<>(
                dtoConverter.publisherToDto(
                        publisherService.add(
                                dtoConverter.publisherDtoToEntity(publisherDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Publisher created successfully with info: \" {}", publisherDto);
        return result;
    }

    @PutMapping
    public ResponseEntity<PublisherDto> update(@Validated(Exist.class) @RequestBody PublisherDto publisherDto,
                                               BindingResult bindingResult) {
        log.info("Updating publisher: {}", publisherDto);
        if (bindingResult.hasErrors()) {
            log.error("Error! Wrong request body.");
            return new ResponseEntity("Error! Wrong request body.", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<PublisherDto> result = new ResponseEntity<>(
                dtoConverter.publisherToDto(
                        publisherService.add(
                                dtoConverter.publisherDtoToEntity(publisherDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Publisher updated successfully with info: \" {}", publisherDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        log.info("Deleting publisher by id = {}", id);
        publisherService.deleteById(id);
        log.info("Publisher with id = {} deleted successfully", id);
        return new ResponseEntity<>("The Publisher deleted successfully.", HttpStatus.OK);
    }
}
