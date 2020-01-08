package ru.animal.world.controller;

import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AbstractController<DTO> {

  @PostMapping
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