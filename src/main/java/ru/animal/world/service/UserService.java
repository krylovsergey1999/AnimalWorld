package ru.animal.world.service;

import java.util.Collection;
import ru.animal.world.dto.UserDto;

public interface UserService {

  UserDto create(UserDto newUserDto);

  UserDto getById(Long id);

  Collection<UserDto> getAll();

  UserDto update(UserDto updateUserDto, Long id);

  boolean delete(Long id);
}