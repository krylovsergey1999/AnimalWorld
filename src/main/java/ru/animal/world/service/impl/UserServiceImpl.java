package ru.animal.world.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.UserDto;
import ru.animal.world.dto.mapper.Mapper;
import ru.animal.world.dto.mapper.UserMapper;
import ru.animal.world.entity.User;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.repository.UserRepository;
import ru.animal.world.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private Mapper<UserDto, User> userMapper = new UserMapper();

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDto create(UserDto newUserDto) {
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
    return userRepository.findById(id).map(userInDB -> {
      if (updateUserDto.getPassword() != null) {
        userInDB.setPassword(updateUserDto.getPassword());
      }
      if (updateUserDto.getEmail() != null) {
        userInDB.setEmail(updateUserDto.getEmail());
      }
      return userMapper.entityToDto(userRepository.saveAndFlush(userInDB));
    }).orElseThrow(() -> new NotFoundException(User.class.getSimpleName()));

//    не сделал так (ниже), потому что при обновлении, к примеру только пароля, email в body будет null и выскочит ошибка бд "поле email не может быть null"
//    updateUserDto.setUserId(id);
//    return userMapper.entityToDto(userRepository.saveAndFlush(userMapper.dtoToEntity(updateUserDto)));
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