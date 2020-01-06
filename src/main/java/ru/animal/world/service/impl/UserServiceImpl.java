package ru.animal.world.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.UserDto;
import ru.animal.world.dto.mapper.UserMapper;
import ru.animal.world.entity.User;
import ru.animal.world.exception.UserNotFoundException;
import ru.animal.world.repository.UserRepository;
import ru.animal.world.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDto create(UserDto newUserDto) {
    User result = userRepository.save(UserMapper.userDtoToEntity(newUserDto));
    return UserMapper.entityUserToDto(result);
  }

  @Override
  public UserDto getById(Long id) {
    return UserMapper.entityUserToDto(userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id)));
  }

  @Override
  public Collection<UserDto> getAll() {
    return userRepository.findAll()
        .stream().map(UserMapper::entityUserToDto)
        .collect(Collectors.toList());
  }

  // Todo fixme if it's necessary
  @Override
  public UserDto update(UserDto updateUserDto, Long id) {
    User updateUser = UserMapper.userDtoToEntity(updateUserDto);
    return userRepository.findById(id).map(userInDB -> {
      if (updateUser.getUserName() != null) {
        userInDB.setUserName(updateUser.getUserName());
      }
      if (updateUser.getUserLastName() != null) {
        userInDB.setUserLastName(updateUser.getUserLastName());
      }
      if (updateUser.getPassword() != null) {
        userInDB.setPassword(updateUser.getPassword());
      }
      if (updateUser.getEmail() != null) {
        userInDB.setEmail(updateUser.getEmail());
      }
      return UserMapper.entityUserToDto(userRepository.saveAndFlush(updateUser));
    }).orElseThrow(() -> new UserNotFoundException(id));
  }

  @Override
  public boolean delete(Long id) {
    try {
      userRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
