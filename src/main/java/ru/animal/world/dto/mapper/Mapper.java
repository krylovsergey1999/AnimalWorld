package ru.animal.world.dto.mapper;

public interface Mapper<Dto, Entity> {

  Dto entityToDto(Entity entity);

  Entity dtoToEntity(Dto dto);
}