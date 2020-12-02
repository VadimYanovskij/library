package com.senla.training.library.dto.converter;

import java.util.List;

public interface ConverterDto<Entity, Dto> {
    Dto entityToDto(Entity entity);

    Entity dtoToEntity(Dto dto);

    List<Dto> entitiesToDtos(List<Entity> entities);

    List<Entity> dtosToEntities(List<Dto> dtos);
}
