package ru.animal.world.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.service.AnimalService;

@RestController
@RequestMapping(value = "/animals")   // http://localhost:8080/users
public class AnimalController implements AbstractController<AnimalDto> {

  private AnimalService animalService;

  @Autowired
  public AnimalController(AnimalService animalService) {
    this.animalService = animalService;
  }


  @Override
  public ResponseEntity<AnimalDto> create(AnimalDto newAnimalDtoRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(animalService.create(newAnimalDtoRequest));
  }

  @Override
  public ResponseEntity<AnimalDto> getById(Long id) {
    return ResponseEntity.ok(animalService.getById(id));
  }

  @Override
  public ResponseEntity<Collection<AnimalDto>> getAll() {
    return ResponseEntity.ok(animalService.getAll());
  }

  @Override
  public ResponseEntity<AnimalDto> update(AnimalDto updatedDtoRequest, Long id) {
    return ResponseEntity.ok().body(animalService.update(updatedDtoRequest, id));
  }

  @Override
  public ResponseEntity<Boolean> delete(Long id) {
    return ResponseEntity.ok().body(animalService.delete(id));
  }
}