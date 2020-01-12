package ru.animal.world.dto.mapper_old;

import java.util.stream.Collectors;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.entity.Animal;

public class AnimalMapper implements Mapper<AnimalDto, Animal> {

  private NoteMapper noteMapper = new NoteMapper();
  private UserMapper userMapper = new UserMapper();

  @Override
  public AnimalDto entityToDto(Animal animal) {
    return AnimalDto.builder()
        .id(animal.getId())
        .animalName(animal.getAnimalName())
        .city(animal.getCity())
        .snapshot(animal.getSnapshot())
        .description(animal.getDescription())
        .gender(animal.getGender())
        .dateOfBirth(animal.getDateOfBirth())
        .notes_animal(animal.getNotes_animal()
            .stream().map(note -> noteMapper.entityToDto(note))
            .collect(Collectors.toSet()))
        .users_animal(userMapper.entityToDto(animal.getUsers_animal()))
        .build();
  }

  @Override
  public Animal dtoToEntity(AnimalDto animalDto) {
    return Animal.builder()
        .id(animalDto.getId())
        .animalName(animalDto.getAnimalName())
        .city(animalDto.getCity())
        .snapshot(animalDto.getSnapshot())
        .description(animalDto.getDescription())
        .gender(animalDto.getGender())
        .dateOfBirth(animalDto.getDateOfBirth())
        .notes_animal(animalDto.getNotes_animal()
            .stream().map(noteDto -> noteMapper.dtoToEntity(noteDto))
            .collect(Collectors.toSet()))
        .users_animal(userMapper.dtoToEntity(animalDto.getUsers_animal()))
        .build();
  }
}