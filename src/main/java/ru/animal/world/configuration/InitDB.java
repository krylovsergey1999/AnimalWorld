package ru.animal.world.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class InitDB {

  // TODO Илья
  CommandLineRunner initDataBase() {
    return args -> {
      log.info("");
    };
  }
}