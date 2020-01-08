package ru.animal.world.dto.mapper;

import ru.animal.world.dto.UserDetailsDto;
import ru.animal.world.entity.UserDetails;

// Todo после обновления всех сущностей
public class UserDetailsMapper implements Mapper<UserDetailsDto, UserDetails> {

  @Override
  public UserDetailsDto entityToDto(UserDetails userDetails) {
    return null;
  }

  @Override
  public UserDetails dtoToEntity(UserDetailsDto userDetailsDto) {
    return null;
  }
}