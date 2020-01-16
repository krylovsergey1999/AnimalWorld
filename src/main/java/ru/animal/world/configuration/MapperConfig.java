package ru.animal.world.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        // стратегия сопоставления полей (строгий: поля все строго последовательно и одинаковы в названии)
        .setMatchingStrategy(MatchingStrategies.STRICT)
        // сопостовление полей включить, сопоставлять поля приватные
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PRIVATE)
        // пропускает поле, если оно null
        .setSkipNullEnabled(true);
    return modelMapper;
  }
}