package ru.animal.world.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.animal.world.utils.City;
import ru.animal.world.utils.Gender;
import ru.animal.world.utils.Role;
import ru.animal.world.utils.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable, BaseDto {

  private Long userId;
  private String username;
  private String userFirstName;
  private String userLastName;
  private Gender gender;
  private LocalDateTime dateOfBirth;
  private String password;
  private Set<Role> roles;
  private String passwordConfirm;
  private String email;
  private City city;
  private String snapshot;
  private String description;
  private Status status;
  private boolean active;
  private LocalDateTime createdOn;
  private LocalDateTime lastLogin;
}