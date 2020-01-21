package ru.animal.world.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;
import ru.animal.world.utils.Role;
import ru.animal.world.utils.Status;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UserDto extends BaseDto implements Serializable {

  private String userName;
  private String userFirstName;
  private String userLastName;
  private Gender gender;
  private LocalDateTime dateOfBirth;
  private String passwordConfirm;
  private String email;
  private String password;
  private City city;
  private String snapshot;
  private String description;
  private Status status;
  private boolean active;
  //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime createdOn;
  private Role role;
  //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime lastLogin;
  private Set<NoteDto> notes;
  private List<DialogDto> dialogs;
  private Set<AnimalDto> animals;

  public UserDto(String username, String userFirstName, String userLastName, Gender gender, LocalDateTime dateOfBirth,
      String passwordConfirm, String email, String password, City city, String snapshot, String description,
      Status status, boolean active, Role role) {
    this.userName = username;
    this.userFirstName = userFirstName;
    this.userLastName = userLastName;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.passwordConfirm = passwordConfirm;
    this.email = email;
    this.password = password;
    this.city = city;
    this.snapshot = snapshot;
    this.description = description;
    this.status = status;
    this.active = active;
    this.role = role;
  }
}