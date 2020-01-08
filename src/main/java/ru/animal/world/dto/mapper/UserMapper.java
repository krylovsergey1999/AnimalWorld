package ru.animal.world.dto.mapper;

import ru.animal.world.dto.UserDto;
import ru.animal.world.entity.User;

public class UserMapper implements Mapper<UserDto, User> {

  @Override
  public UserDto entityToDto(User user) {
    return UserDto.builder()
        .userId(user.getUserId())
        .password(user.getPassword())
        .email(user.getEmail())
        .build();
  }

  @Override
  public User dtoToEntity(UserDto userDto) {
    return User.builder()
        .userId(userDto.getUserId())
        .password(userDto.getPassword())
        .email(userDto.getEmail())
        .build();
  }
}