package ru.animal.world.dto;

import java.time.LocalDateTime;
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
public class NoteDto extends BaseDto {

  private String noteName;
  private String description;
  private LocalDateTime createDate;
  private Set<CommentDto> comments;
  private UserDto author_note;
  private Set<AnimalDto> animals_note;

  public NoteDto(String noteName, String description, LocalDateTime createDate) {
    this.noteName = noteName;
    this.description = description;
    this.createDate = createDate;
  }
}