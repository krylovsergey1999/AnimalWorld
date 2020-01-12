package ru.animal.world.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.UserDto;
import ru.animal.world.entity.User;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {

  @Autowired
  public UserMapper() {
    super(User.class, UserDto.class);
  }
}