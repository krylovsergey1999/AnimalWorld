package ru.animal.world.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.UserDetailsDto;
import ru.animal.world.dto.mapper.Mapper;
import ru.animal.world.dto.mapper.UserDetailsMapper;
import ru.animal.world.entity.UserDetails;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.repository.UserDetailsRepository;
import ru.animal.world.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserDetailsRepository userDetailsRepository;
  private Mapper<UserDetailsDto, UserDetails> userDetailsMapper = new UserDetailsMapper();

  @Autowired
  public UserDetailsServiceImpl(UserDetailsRepository UserDetailsRepository) {
    this.userDetailsRepository = UserDetailsRepository;
  }

  @Override
  public UserDetailsDto create(UserDetailsDto newUserDetailsDto) {
    UserDetails result = userDetailsRepository.save(userDetailsMapper.dtoToEntity(newUserDetailsDto));
    return userDetailsMapper.entityToDto(result);
  }

  @Override
  public UserDetailsDto getById(Long id) {
    return userDetailsMapper.entityToDto(userDetailsRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(UserDetails.class.getSimpleName())));
  }

  @Override
  public Collection<UserDetailsDto> getAll() {
    return userDetailsRepository.findAll()
        .stream().map(userDetailsMapper::entityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public UserDetailsDto update(UserDetailsDto updateUserDetailsDto, Long id) {
    return userDetailsRepository.findById(id).map(UserDetailsInDB -> {
      // Todo после обновления всех сущностей
      return userDetailsMapper.entityToDto(userDetailsRepository.saveAndFlush(UserDetailsInDB));
    }).orElseThrow(() -> new NotFoundException(UserDetails.class.getSimpleName()));
  }

  @Override
  public boolean delete(Long id) {
    try {
      userDetailsRepository.deleteById(id);
      return true;
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundException(UserDetails.class.getSimpleName());
    }
  }
}