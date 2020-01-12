package ru.animal.world.mapper;

import ru.animal.world.dto.BaseDto;
import ru.animal.world.entity.BaseEntity;

public interface Mapper<Entity extends BaseEntity, Dto extends BaseDto> {

  Dto entityToDto(Entity entity);

  Entity dtoToEntity(Dto dto);
}