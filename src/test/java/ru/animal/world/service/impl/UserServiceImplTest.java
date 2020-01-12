package ru.animal.world.service.impl;

import static ru.animal.world.util.TestObjects.animal1;
import static ru.animal.world.util.TestObjects.animal2;
import static ru.animal.world.util.TestObjects.animalDto1;
import static ru.animal.world.util.TestObjects.animalDto2;
import static ru.animal.world.util.TestObjects.dialog1;
import static ru.animal.world.util.TestObjects.dialog2;
import static ru.animal.world.util.TestObjects.dialogDto1;
import static ru.animal.world.util.TestObjects.dialogDto2;
import static ru.animal.world.util.TestObjects.note1;
import static ru.animal.world.util.TestObjects.note2;
import static ru.animal.world.util.TestObjects.noteDto1;
import static ru.animal.world.util.TestObjects.noteDto2;
import static ru.animal.world.util.TestObjects.user1;
import static ru.animal.world.util.TestObjects.userDto1;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.animal.world.dto.UserDto;
import ru.animal.world.entity.User;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.mapper.UserMapper;
import ru.animal.world.repository.AnimalRepository;
import ru.animal.world.repository.DialogRepository;
import ru.animal.world.repository.NoteRepository;
import ru.animal.world.repository.UserRepository;
import ru.animal.world.service.UserService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

  @Autowired
  UserRepository userRepository;
  @Autowired
  NoteRepository noteRepository;
  @Autowired
  AnimalRepository animalRepository;
  @Autowired
  DialogRepository dialogRepository;
  @Autowired
  UserMapper userMapper;

  @Autowired
  UserService userService;

  @BeforeEach
  void setUp() {
    user1.setNotes(Set.of(note1, note2));
    user1.setDialogs(List.of(dialog1, dialog2));
    user1.setAnimals(Set.of(animal1, animal2));

    userDto1.setNotes(Set.of(noteDto1, noteDto2));
    userDto1.setDialogs(List.of(dialogDto1, dialogDto2));
    userDto1.setAnimals(Set.of(animalDto1, animalDto2));

    init();
  }

  private void init() {
    animalRepository.save(animal1);
    animalRepository.save(animal2);
    noteRepository.save(note1);
    noteRepository.save(note2);
    dialogRepository.save(dialog1);
    dialogRepository.save(dialog2);

    userRepository.saveAndFlush(user1);
  }

  @Test
  void create() {
  }

  @Test
  void getById() {
    System.out.println("до:  userDto1 id = " + userDto1.getId());
    // TODO  null pointer WTF???
    System.out.println(userService.getById(user1.getId()).toString());
  }

  @Test
  void getAll() {
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }

  @Test
  void mapperTest() {
    User user = userRepository.findById(user1.getId())
        .orElseThrow(() -> new NotFoundException(User.class.getSimpleName()));

    UserDto userDto = userMapper.entityToDto(user);

    Assertions.assertEquals(userDto.getId(), user.getId());

  }
}