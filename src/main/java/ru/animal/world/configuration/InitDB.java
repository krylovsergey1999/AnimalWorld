package ru.animal.world.configuration;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.animal.world.dto.AnimalDto;
import ru.animal.world.dto.UserDto;
import ru.animal.world.service.AnimalService;
import ru.animal.world.service.CommentService;
import ru.animal.world.service.DialogService;
import ru.animal.world.service.MessageService;
import ru.animal.world.service.NoteService;
import ru.animal.world.service.UserService;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;
import ru.animal.world.utils.Role;
import ru.animal.world.utils.Status;

@Slf4j
@Configuration
public class InitDB {

  @Bean
  CommandLineRunner initDataBase(AnimalService animalService, CommentService commentService,
      DialogService dialogService, MessageService messageService, NoteService noteService, UserService userService) {
    UserDto userDto1 = new UserDto("sam", "Илья", "Булавин", Gender.MALE, LocalDateTime.of(1995, 11, 18, 0, 0),
        "password1", "sam47kon@mail.ru", "password1", City.ROSTOV_ON_DON, "картинка1", "я есть конь", Status.ONLINE,
        true, Role.USER);
    UserDto userDto2 = new UserDto("serega", "Сергей", "Крылов", Gender.MALE, LocalDateTime.of(1998, 11, 18, 0, 0),
        "password2", "krylov_sergey@mail.ru", "password2", City.SAINT_PETERSBURG, "картинка2", "я есть Серега",
        Status.ONLINE, true, Role.USER);
    UserDto userDto3 = new UserDto("andrew", "Андрей", "Фамилия", Gender.MALE, LocalDateTime.of(1998, 10, 18, 0, 0),
        "password3", "korzh17@mail.ru", "password3", City.MOSCOW, "картинка3", "я есть Андрюха", Status.ONLINE, true,
        Role.USER);
    AnimalDto animalDto1 = new AnimalDto("Пёс", City.ROSTOV_ON_DON, "картинка_пса", "пёс есть пёс", Gender.MALE,
        LocalDateTime.of(2005, 11, 18, 0, 0));
    AnimalDto animalDto2 = new AnimalDto("Мурзик", City.SAINT_PETERSBURG, "мурзик_фото_в_купальнике",
        "этот мурзик уже надоел", Gender.MALE, LocalDateTime.of(2010, 11, 18, 0, 0));
    AnimalDto animalDto3 = new AnimalDto("Вега", City.ROSTOV_ON_DON, "вега_фото", "Сама грация", Gender.FEMALE,
        LocalDateTime.of(2015, 11, 18, 0, 0));
    AnimalDto animalDto4 = new AnimalDto("Муся", City.SAINT_PETERSBURG, "муся_фото", "ну что сказать...", Gender.FEMALE,
        LocalDateTime.of(2016, 11, 18, 0, 0));
//    NoteDto noteDto1 = new NoteDto() TODO now
    return args -> {
      log.info("Initial Database: filling...");

      log.info("Create 3 users:");
      UserDto userDto_1 = userService.create(userDto1);
      log.info("Create user1: " + userDto_1);
      UserDto userDto_2 = userService.create(userDto2);
      log.info("Create user2: " + userDto_2);
      UserDto userDto_3 = userService.create(userDto3);
      log.info("Create user3: " + userDto_3);

      log.info("Create 4 animals:");
      animalDto1.setUserId(userDto_1.getId());
      AnimalDto animalDto_1 = animalService.create(animalDto1);
      log.info("Create animal1: " + animalDto_1);
      animalDto2.setUserId(userDto_2.getId());
      AnimalDto animalDto_2 = animalService.create(animalDto2);
      log.info("Create animal2: " + animalDto_2);
      animalDto3.setUserId(userDto_3.getId());
      AnimalDto animalDto_3 = animalService.create(animalDto3);
      log.info("Create animal3: " + animalDto_3);
      animalDto4.setUserId(userDto_1.getId());
      AnimalDto animalDto_4 = animalService.create(animalDto4);
      log.info("Create animal4: " + animalDto_4);
    };
  }
}