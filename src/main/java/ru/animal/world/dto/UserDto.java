package ru.animal.world.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

  private Long userId;
  private String userName;
  private String userLastName;
  private String password;
  private String email;
  private Date created_on;
  private Date lastLogin;
}