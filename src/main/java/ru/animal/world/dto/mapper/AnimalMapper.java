package ru.animal.world.dto.mapper;

import ru.animal.world.dto.AnimalDto;
import ru.animal.world.entity.Animal;

// Todo после обновления всех сущностей
public class AnimalMapper implements Mapper<AnimalDto, Animal> {

  @Override
  public AnimalDto entityToDto(Animal animal) {
    return null;
  }

  @Override
  public Animal dtoToEntity(AnimalDto animalDto) {
    return null;
  }
}