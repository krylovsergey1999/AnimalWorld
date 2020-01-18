package ru.animal.world.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.animal.world.dto.UserDto;
import ru.animal.world.dto.mapper.Mapper;
import ru.animal.world.dto.mapper.UserMapper;
import ru.animal.world.entity.User;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.repository.UserRepository;
import ru.animal.world.service.UserService;
import ru.animal.world.utils.Role;

@Service
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private Mapper<UserDto, User> userMapper = new UserMapper();

  @Autowired
  public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  public UserDto create(UserDto newUserDto) {
    boolean isConfirm = newUserDto.getPassword() != null && newUserDto.getPassword().equals(newUserDto.getPasswordConfirm());
    User userByEmail = userRepository.findByEmail(newUserDto.getEmail());
    if (!isConfirm || StringUtils.isEmpty(newUserDto.getEmail()) || userByEmail != null) {
      return new UserDto();
    }
    newUserDto.setPassword(passwordEncoder.encode(newUserDto.getPasswordConfirm()));
    newUserDto.setCreatedOn(LocalDateTime.now());
    newUserDto.setActive(true);
    newUserDto.setRoles(Collections.singleton(Role.USER));
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
      if (updateUserDto.getUsername() != null) {
        userInDB.setUsername(updateUserDto.getUsername());
      }
      if (updateUserDto.getUserFirstName() != null) {
        userInDB.setUsername(updateUserDto.getUsername());
      }
      if (updateUserDto.getUserLastName() != null) {
        userInDB.setUserLastName(updateUserDto.getUserLastName());
      }
      if (updateUserDto.getGender() != null) {
        userInDB.setGender(updateUserDto.getGender());
      }
      if (updateUserDto.getDateOfBirth() != null) {
        userInDB.setDateOfBirth(updateUserDto.getDateOfBirth());
      }
      if (updateUserDto.getPassword() != null) {
        userInDB.setPassword(updateUserDto.getPassword());
      }
      if (updateUserDto.getEmail() != null) {
        userInDB.setEmail(updateUserDto.getEmail());
      }

      if (updateUserDto.getCity() != null) {
        userInDB.setCity(updateUserDto.getCity());
      }
      if (updateUserDto.getSnapshot() != null) {
        userInDB.setSnapshot(updateUserDto.getSnapshot());
      }
      if (updateUserDto.getDescription() != null) {
        userInDB.setDescription(updateUserDto.getDescription());
      }
      if (updateUserDto.getStatus() != null) {
        userInDB.setStatus(updateUserDto.getStatus());
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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username == null || StringUtils.isEmpty(username)) {
      throw new UsernameNotFoundException("UserName not found");
    }
    return userRepository.findByUsername(username);
  }
}