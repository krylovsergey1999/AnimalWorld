package ru.animal.world.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;
import ru.animal.world.utils.Status;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto implements Serializable {

  private String userName;
  private String userLastName;
  private Gender gender;
  private LocalDateTime dateOfBirth;
  private String email;
  private String password;
  private City city;
  private String snapshot;
  private String description;
  private Status status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime createdOn;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime lastLogin;
  private Set<NoteDto> notes;
  private List<DialogDto> dialogs;
  private Set<AnimalDto> animals;

  public UserDto(String userName, String userLastName, Gender gender, LocalDateTime dateOfBirth, String email,
      String password, City city, String snapshot, String description, Status status, LocalDateTime createdOn,
      LocalDateTime lastLogin) {
    this.userName = userName;
    this.userLastName = userLastName;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.password = password;
    this.city = city;
    this.snapshot = snapshot;
    this.description = description;
    this.status = status;
    this.snapshot = snapshot;
    this.createdOn = createdOn;
    this.lastLogin = lastLogin;
  }
}