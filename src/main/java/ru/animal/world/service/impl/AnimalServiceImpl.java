package ru.animal.world.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.entity.Animal;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.repository.AnimalRepository;
import ru.animal.world.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {

  private final AnimalRepository animalRepository;
  //  private Mapper<AnimalDto, Animal> animalMapper = new AnimalMapper();
  private ru.animal.world.mapper.AnimalMapper animalMapper = new ru.animal.world.mapper.AnimalMapper();

  @Autowired
  public AnimalServiceImpl(AnimalRepository AnimalRepository) {
    this.animalRepository = AnimalRepository;
  }

  @Override
  public AnimalDto create(AnimalDto newAnimalDto) {
    Animal result = animalRepository.save(animalMapper.dtoToEntity(newAnimalDto));
    return animalMapper.entityToDto(result);
  }

  @Override
  public AnimalDto getById(Long id) {
    return animalMapper.entityToDto(animalRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Animal.class.getSimpleName())));
  }

  @Override
  public Collection<AnimalDto> getAll() {
    return animalRepository.findAll()
        .stream().map(animalMapper::entityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public AnimalDto update(AnimalDto updateAnimalDto, Long id) {
    Animal animal = animalRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Animal.class.getSimpleName()));
    animal = animalMapper.dtoToEntity(updateAnimalDto);
    animal.setId(id);
    return animalMapper.entityToDto(animalRepository.saveAndFlush(animal));
  }

  @Override
  public boolean delete(Long id) {
    try {
      animalRepository.deleteById(id);
      return true;
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundException(Animal.class.getSimpleName());
    }
  }
}