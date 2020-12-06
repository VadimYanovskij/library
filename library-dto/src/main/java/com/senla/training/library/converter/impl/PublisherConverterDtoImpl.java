package com.senla.training.library.converter.impl;

import com.senla.training.library.dto.PublisherDto;
import com.senla.training.library.converter.PublisherConverterDto;
import com.senla.training.library.entity.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PublisherConverterDtoImpl implements PublisherConverterDto {
    @Override
    public PublisherDto entityToDto(Publisher publisher) {
        log.info("Converting publisher with id = {} to com.senla.training.library.dto", publisher.getId());
        PublisherDto result = new PublisherDto(
                publisher.getId(),
                publisher.getPublisherName(),
                publisher.getPublisherCity()
        );
        log.info("Publisher converted successfully");
        return result;
    }

    @Override
    public Publisher dtoToEntity(PublisherDto publisherDto) {
        log.info("Converting publisherDto with id = {} to publisher", publisherDto.getId());
        Publisher result = new Publisher(
                publisherDto.getId(),
                publisherDto.getPublisherName(),
                publisherDto.getPublisherCity()
        );
        log.info("PublisherDto converted successfully");
        return result;
    }

    @Override
    public List<PublisherDto> entitiesToDtos(List<Publisher> publishers) {
        log.info("Converting publishers to dtos");
        List<PublisherDto> result = publishers.stream()
                .map(publisher -> entityToDto(publisher))
                .collect(Collectors.toList());
        log.info("Publishers converted successfully");
        return result;
    }

    @Override
    public List<Publisher> dtosToEntities(List<PublisherDto> publisherDtos) {
        log.info("Converting publisherDtos to publishers");
        List<Publisher> result = publisherDtos.stream()
                .map(publisherDto -> dtoToEntity(publisherDto))
                .collect(Collectors.toList());
        log.info("PublisherDtos converted successfully");
        return result;
    }
}
