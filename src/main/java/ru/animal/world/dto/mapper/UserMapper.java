package ru.animal.world.dto.mapper;

import ru.animal.world.dto.UserDto;
import ru.animal.world.entity.User;

public class UserMapper implements Mapper<UserDto, User> {

  @Override
  public UserDto entityToDto(User user) {
    return UserDto.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .userFirstName(user.getUserFirstName())
        .userLastName(user.getUserLastName())
        .gender(user.getGender())
        .dateOfBirth(user.getDateOfBirth())
        .password(user.getPassword())
        .roles(user.getRoles())
        .email(user.getEmail())
        .city(user.getCity())
        .snapshot(user.getSnapshot())
        .description(user.getDescription())
        .status(user.getStatus())
        .active(user.getActive())
        .createdOn(user.getCreatedOn())
        .lastLogin(user.getLastLogin())
        .build();
  }

  @Override
  public User dtoToEntity(UserDto userDto) {
    return User.builder()
        .userId(userDto.getUserId())
        .username(userDto.getUsername())
        .userFirstName(userDto.getUserFirstName())
        .userLastName(userDto.getUserLastName())
        .gender(userDto.getGender())
        .dateOfBirth(userDto.getDateOfBirth())
        .password(userDto.getPassword())
        .roles(userDto.getRoles())
        .email(userDto.getEmail())
        .city(userDto.getCity())
        .snapshot(userDto.getSnapshot())
        .description(userDto.getDescription())
        .status(userDto.getStatus())
        .active(userDto.isActive())
        .createdOn(userDto.getCreatedOn())
        .lastLogin(userDto.getLastLogin())
        .build();
  }
}