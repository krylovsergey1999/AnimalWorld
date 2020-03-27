package ru.animal.world.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.animal.world.dto.UserDto;
import ru.animal.world.entity.User;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.mapper.UserMapper;
import ru.animal.world.repository.UserRepository;
import ru.animal.world.service.UserService;
import ru.animal.world.utils.Role;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private UserMapper userMapper;

  @Autowired
  public UserServiceImpl(PasswordEncoder passwordEncoder, UserMapper mapper, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.userMapper = mapper;
  }

  @Override
  public UserDto create(UserDto newUserDto) {
//    boolean isConfirm = newUserDto.getPassword() != null && newUserDto.getPassword().equals(newUserDto.getPasswordConfirm());
//    User userByEmail = userRepository.findByEmail(newUserDto.getEmail());
//    if (!isConfirm || StringUtils.isEmpty(newUserDto.getEmail()) || userByEmail != null) {
//      return new UserDto();
//    }
    newUserDto.setPassword(passwordEncoder.encode(newUserDto.getPasswordConfirm()));
    newUserDto.setCreatedOn(LocalDateTime.now());
    newUserDto.setActive(true);
    newUserDto.setRole(Role.USER);
    log.info("Before create user : {}", newUserDto);
    User result = userRepository.save(userMapper.dtoToEntity(newUserDto));
    log.info("After create user: {}", result);
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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username == null || StringUtils.isEmpty(username)) {
      throw new UsernameNotFoundException("UserName not found");
    }
    UserDetails resultUser = userRepository.findByUserName(username);
    return resultUser;
  }
}