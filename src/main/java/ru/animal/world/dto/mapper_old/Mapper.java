package ru.animal.world.dto.mapper_old;

public interface Mapper<Dto, Entity> {

  Dto entityToDto(Entity entity);

  Entity dtoToEntity(Dto dto);
}