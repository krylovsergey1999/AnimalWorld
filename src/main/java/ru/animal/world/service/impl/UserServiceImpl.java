package ru.animal.world.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.UserDto;
import ru.animal.world.entity.User;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.mapper.UserMapper;
import ru.animal.world.repository.UserRepository;
import ru.animal.world.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private UserMapper userMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
    this.userRepository = userRepository;
    this.userMapper = mapper;
  }

  @Override
  public UserDto create(UserDto newUserDto) {
    newUserDto.setCreatedOn(LocalDateTime.now());
    User result = userRepository.save(userMapper.dtoToEntity(newUserDto));
    return userMapper.entityToDto(result);
  }

  @Override
  public UserDto getById(Long id) {
    return userMapper.entityToDto(userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(User.class.getSimpleName())));
  }

  @Override
  public Collection<UserDto> getAll() {
    return userRepository.findAll()
        .stream().map(userMapper::entityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto update(UserDto updateUserDto, Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class.getSimpleName()));
    user = userMapper.dtoToEntity(updateUserDto);
    user.setId(id);
    return userMapper.entityToDto(userRepository.saveAndFlush(user));
  }

  @Override
  public boolean delete(Long id) {
    try {
      userRepository.deleteById(id);
      return true;
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundException(User.class.getSimpleName());
    }
  }
}