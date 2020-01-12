package ru.animal.world.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.entity.Animal;

@Component
public class AnimalMapper extends AbstractMapper<Animal, AnimalDto> {

  @Autowired
  public AnimalMapper() {
    super(Animal.class, AnimalDto.class);
  }
}