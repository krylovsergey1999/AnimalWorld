package ru.animal.world.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

public enum City {
  ROSTOV_ON_DON("Ростов-на-Дону", "RND"),
  MOSCOW("Москва", "MSC"),
  SAINT_PETERSBURG("Санкт-Петербург", "SPB");

  @Getter
  @Setter
  @JsonValue
  private String name;

  @Getter
  @Setter
  private String code;

  City(String name, String code) {
    this.name = name;
    this.code = code;
  }

  @JsonCreator
  public static City forValues(@JsonProperty("code") String code) {
    for (City city : City.values()) {
      if (city.code.equals(code)) {
        return city;
      }
    }
    return null;
  }
}