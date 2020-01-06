package ru.animal.world.dto.mapper;

import ru.animal.world.dto.UserDto;
import ru.animal.world.entity.User;

public class UserMapper {

  public static UserDto entityUserToDto(User user) {
    return UserDto.builder()
        .userId(user.getUserId())
        .userName(user.getUserName())
        .userLastName(user.getUserLastName())
        .email(user.getEmail())
        .created_on(user.getCreated_on())
        .lastLogin(user.getLastLogin())
        .build();
  }

  public static User userDtoToEntity(UserDto userDto) {
    return User.builder()
        .userId(userDto.getUserId())
        .userName(userDto.getUserName())
        .userLastName(userDto.getUserLastName())
        .email(userDto.getEmail())
        .created_on(userDto.getCreated_on())
        .lastLogin(userDto.getLastLogin())
        .build();
  }
}