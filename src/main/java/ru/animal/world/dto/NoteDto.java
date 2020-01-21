package ru.animal.world.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class NoteDto extends BaseDto {

  private String noteName;
  private String description;
  private LocalDateTime createDate;
  private Set<CommentDto> comments;
  private Long authorId;
  private Set<AnimalDto> animalsNote;

  public NoteDto(String noteName, String description, LocalDateTime createDate) {
    this.noteName = noteName;
    this.description = description;
    this.createDate = createDate;
  }
}