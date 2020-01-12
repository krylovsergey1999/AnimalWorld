package ru.animal.world.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.NoteDto;
import ru.animal.world.entity.Note;

@Component
public class NoteMapper extends AbstractMapper<Note, NoteDto> {

  @Autowired
  public NoteMapper() {
    super(Note.class, NoteDto.class);
  }
}