package ru.animal.world.dto.mapper_old;

import java.util.stream.Collectors;
import ru.animal.world.dto.UserDto;
import ru.animal.world.entity.User;

public class UserMapper implements Mapper<UserDto, User> {

  NoteMapper noteMapper = new NoteMapper();
  DialogMapper dialogMapper = new DialogMapper();
  AnimalMapper animalMapper = new AnimalMapper();

  @Override
  public UserDto entityToDto(User user) {
    return UserDto.builder()
        .id(user.getId())
        .userName(user.getUserName())
        .userLastName(user.getUserLastName())
        .gender(user.getGender())
        .dateOfBirth(user.getDateOfBirth())
        .password(user.getPassword())
        .email(user.getEmail())
        .city(user.getCity())
        .snapshot(user.getSnapshot())
        .description(user.getDescription())
        .status(user.getStatus())
        .createdOn(user.getCreatedOn())
        .lastLogin(user.getLastLogin())
        .notes(user.getNotes()
            .stream().map(note -> noteMapper.entityToDto(note))
            .collect(Collectors.toSet()))
        .dialogs(user.getDialogs()
            .stream().map(dialog -> dialogMapper.entityToDto(dialog))
            .collect(Collectors.toList()))
        .animals(user.getAnimals()
            .stream().map(animal -> animalMapper.entityToDto(animal))
            .collect(Collectors.toSet()))
        .build();
  }

  @Override
  public User dtoToEntity(UserDto userDto) {
    return User.builder()
        .id(userDto.getId())
        .userName(userDto.getUserName())
        .userLastName(userDto.getUserLastName())
        .gender(userDto.getGender())
        .dateOfBirth(userDto.getDateOfBirth())
        .password(userDto.getPassword())
        .email(userDto.getEmail())
        .city(userDto.getCity())
        .snapshot(userDto.getSnapshot())
        .description(userDto.getDescription())
        .status(userDto.getStatus())
        .createdOn(userDto.getCreatedOn())
        .lastLogin(userDto.getLastLogin())
        .notes(userDto.getNotes()
            .stream().map(noteDto -> noteMapper.dtoToEntity(noteDto))
            .collect(Collectors.toSet()))
        .dialogs(userDto.getDialogs()
            .stream().map(dialogDto -> dialogMapper.dtoToEntity(dialogDto))
            .collect(Collectors.toList()))
        .animals(userDto.getAnimals()
            .stream().map(animalDto -> animalMapper.dtoToEntity(animalDto))
            .collect(Collectors.toSet()))
        .build();
  }
}