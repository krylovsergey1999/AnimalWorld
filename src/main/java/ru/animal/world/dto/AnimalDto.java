package ru.animal.world.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class AnimalDto extends BaseDto {

  private String animalName;
  private City city;
  private String snapshot;
  private String description;
  private Gender gender;
  private LocalDateTime dateOfBirth;
  private Set<NoteDto> notesAnimal;
  private Long userId;

  public AnimalDto(String animalName, City city, String snapshot, String description, Gender gender,
      LocalDateTime dateOfBirth) {
    this.animalName = animalName;
    this.city = city;
    this.snapshot = snapshot;
    this.description = description;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
  }
}