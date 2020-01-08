package ru.animal.world.dto.mapper;

import ru.animal.world.dto.NoteDto;
import ru.animal.world.entity.Note;

// Todo после обновления всех сущностей
public class NoteMapper implements Mapper<NoteDto, Note> {

  @Override
  public NoteDto entityToDto(Note note) {
    return null;
  }

  @Override
  public Note dtoToEntity(NoteDto noteDto) {
    return null;
  }
}