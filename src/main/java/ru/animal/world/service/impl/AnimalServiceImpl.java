package ru.animal.world.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.entity.Animal;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.mapper.AnimalMapper;
import ru.animal.world.mapper.UserMapper;
import ru.animal.world.repository.AnimalRepository;
import ru.animal.world.service.AnimalService;
import ru.animal.world.service.UserService;

@Service
public class AnimalServiceImpl implements AnimalService {

  private final AnimalRepository animalRepository;
  private AnimalMapper animalMapper;
  private UserMapper userMapper;
  private UserService userService;

  @Autowired
  public AnimalServiceImpl(AnimalRepository animalRepository, AnimalMapper mapper, UserService userService,
      UserMapper userMapper) {
    this.animalRepository = animalRepository;
    this.animalMapper = mapper;
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @Override
  public AnimalDto create(AnimalDto newAnimalDto) {
    Animal entity = animalMapper.dtoToEntity(newAnimalDto);
    entity.setUsersAnimal(userMapper.dtoToEntity(
        userService.getById(
            newAnimalDto.getUserId())));
    return animalMapper.entityToDto(animalRepository.save(entity));
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
    if (updateAnimalDto.getUserId() == null) {
      updateAnimalDto.setUserId(animal.getUsersAnimal().getId());
    }
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