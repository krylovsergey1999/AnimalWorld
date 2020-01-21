package ru.animal.world.util;

import java.time.LocalDateTime;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.dto.DialogDto;
import ru.animal.world.dto.NoteDto;
import ru.animal.world.dto.UserDto;
import ru.animal.world.entity.Animal;
import ru.animal.world.entity.Dialog;
import ru.animal.world.entity.Note;
import ru.animal.world.entity.User;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;
import ru.animal.world.utils.Role;
import ru.animal.world.utils.Status;

public class TestObjects {

  public static User user1 = new User(1L, "username", "Илья", "Булавин", Gender.MALE, LocalDateTime.now(), "123@mail.ru",
      "пароль", City.MOSCOW, "картинка", "оооопииисаниие", Status.ONLINE, true, LocalDateTime.now(), LocalDateTime.now(), Role.USER);

  public static Note note1 = new Note(1L, "имя заметки1", "описание1", LocalDateTime.now());
  public static Note note2 = new Note(2L, "имя заметки2", "описание2", LocalDateTime.now());

  public static Dialog dialog1 = new Dialog(1L, "текст бади1");
  public static Dialog dialog2 = new Dialog(2L, "текст бади2");

  public static Animal animal1 = new Animal(1L, "Имя животного 1", City.ROSTOV_ON_DON, "картинка_живот1",
      "описание животного2", Gender.FEMALE, LocalDateTime.now());
  public static Animal animal2 = new Animal(2L, "Имя животного 2", City.MOSCOW, "картинка_живот2",
      "описание животного2", Gender.MALE, LocalDateTime.now());


  public static UserDto userDto1 = new UserDto("username", "Илья", "Булавин", Gender.MALE, LocalDateTime.now(), "123@mail.ru",
      "пароль", City.MOSCOW, "картинка", "оооопииисаниие", Status.ONLINE, true, LocalDateTime.now(), LocalDateTime.now(), Role.USER);

  public static NoteDto noteDto1 = new NoteDto("имя заметки1", "описание1", LocalDateTime.now());
  public static NoteDto noteDto2 = new NoteDto("имя заметки2", "описание2", LocalDateTime.now());

  public static DialogDto dialogDto1 = new DialogDto("текст бади1");
  public static DialogDto dialogDto2 = new DialogDto("текст бади2");

  public static AnimalDto animalDto1 = new AnimalDto("Имя животного 1", City.ROSTOV_ON_DON, "картинка_живот1",
      "описание животного2", Gender.FEMALE, LocalDateTime.now());
  public static AnimalDto animalDto2 = new AnimalDto("Имя животного 2", City.MOSCOW, "картинка_живот2",
      "описание животного2", Gender.MALE, LocalDateTime.now());

}