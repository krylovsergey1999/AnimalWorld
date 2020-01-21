package ru.animal.world.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

// http://localhost:8080/users
public interface AbstractController<DTO> {

  @PostMapping("/create")
  ResponseEntity<DTO> create(@RequestBody DTO newInstanceRequest);

  @GetMapping("/{id}")
  ResponseEntity<DTO> getById(@PathVariable Long id);

  @GetMapping
  ResponseEntity<Collection<DTO>> getAll();

  @PutMapping("/{id}")
  ResponseEntity<DTO> update(@RequestBody DTO updatedInstanceRequest, @PathVariable Long id);

  @DeleteMapping("/{id}")
  ResponseEntity<Boolean> delete(@PathVariable Long id);
}
