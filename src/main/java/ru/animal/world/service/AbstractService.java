package ru.animal.world.service;

import java.util.Collection;

public interface AbstractService<DTO> {

  DTO create(DTO newInstanceDto);

  DTO getById(Long id);

  Collection<DTO> getAll();

  DTO update(DTO updateInstanceDto, Long id);

  boolean delete(Long id);
}