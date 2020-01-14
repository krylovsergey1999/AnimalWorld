package ru.animal.world.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class DialogDto extends BaseDto {

  private String textBody;
  private Set<MessageDto> messages;
  private Set<UserDto> usersDialog;

  public DialogDto(String textBody) {
    this.textBody = textBody;
  }
}