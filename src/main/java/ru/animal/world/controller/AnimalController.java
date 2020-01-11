package ru.animal.world.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.animal.world.dto.AnimalDto;

import java.util.Collection;

@RestController
@RequestMapping(value = "/animals")
public class AnimalController implements AbstractController<AnimalDto> {


    @Override
    public ResponseEntity<AnimalDto> create(AnimalDto newInstanceRequest) {
        return null;
    }

    @Override
    public ResponseEntity<AnimalDto> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Collection<AnimalDto>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<AnimalDto> update(AnimalDto updatedInstanceRequest, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return null;
    }
}
