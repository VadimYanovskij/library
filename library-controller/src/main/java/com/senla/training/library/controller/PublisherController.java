package com.senla.training.library.controller;

import com.senla.training.library.dto.PublisherDto;

import com.senla.training.library.converter.PublisherConverterDto;
import com.senla.training.library.transfer.Exist;
import com.senla.training.library.transfer.New;
import com.senla.training.library.service.PublisherService;

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
@RequestMapping("v1/publishers")
public class PublisherController {

    private final PublisherService publisherService;
    private final PublisherConverterDto publisherConverterDto;
    private final MessageSource messageSource;

    public PublisherController(PublisherService publisherService,
                               PublisherConverterDto publisherConverterDto,
                               MessageSource messageSource) {
        this.publisherService = publisherService;
        this.publisherConverterDto = publisherConverterDto;
        this.messageSource = messageSource;
    }

    @GetMapping
    public ResponseEntity<List<PublisherDto>> findAll() {
        log.info("Listing publishers");
        ResponseEntity<List<PublisherDto>> result = new ResponseEntity<>(
                publisherConverterDto.entitiesToDtos(
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
                publisherConverterDto.entityToDto(
                        publisherService.findById(id)
                ),
                HttpStatus.OK
        );
        log.info("Publisher with id = {} found", id);
        return result;
    }

    @PostMapping
    public ResponseEntity<PublisherDto> add(
            @Validated(New.class) @RequestBody PublisherDto publisherDto,
            BindingResult bindingResult) {
        log.info("Creating publisher: {}", publisherDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<PublisherDto> result = new ResponseEntity<>(
                publisherConverterDto.entityToDto(
                        publisherService.add(
                                publisherConverterDto.dtoToEntity(publisherDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Publisher created successfully with info: \" {}", publisherDto);
        return result;
    }

    @PutMapping
    public ResponseEntity<PublisherDto> update(
            @Validated(Exist.class) @RequestBody PublisherDto publisherDto,
            BindingResult bindingResult) {
        log.info("Updating publisher: {}", publisherDto);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Wrong request body!");
        }
        ResponseEntity<PublisherDto> result = new ResponseEntity<>(
                publisherConverterDto.entityToDto(
                        publisherService.add(
                                publisherConverterDto.dtoToEntity(publisherDto)
                        )
                ),
                HttpStatus.OK
        );
        log.info("Publisher updated successfully with info: \" {}", publisherDto);
        return result;
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id,
                                             @RequestParam(value = "locale",
                                                     required = false) Locale locale) {
        log.info("Deleting publisher by id = {}", id);
        publisherService.deleteById(id);
        log.info("Publisher with id = {} deleted successfully", id);
        return new ResponseEntity<>(messageSource.getMessage(
                "label.DeletedSuccessfully", null, locale),
                HttpStatus.OK);
    }
}
