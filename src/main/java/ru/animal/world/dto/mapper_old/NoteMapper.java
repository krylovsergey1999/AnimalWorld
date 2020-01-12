package ru.animal.world.dto.mapper_old;

import java.util.stream.Collectors;
import ru.animal.world.dto.NoteDto;
import ru.animal.world.entity.Note;

public class NoteMapper implements Mapper<NoteDto, Note> {

  CommentMapper commentMapper = new CommentMapper();
  UserMapper userMapper = new UserMapper();
  AnimalMapper animalMapper = new AnimalMapper();

  @Override
  public NoteDto entityToDto(Note note) {
    return NoteDto.builder()
        .id(note.getId())
        .noteName(note.getNoteName())
        .description(note.getDescription())
        .createDate(note.getCreateDate())
        .comments(note.getComments()
            .stream().map(comment -> commentMapper.entityToDto(comment))
            .collect(Collectors.toSet()))
        .author_note(userMapper.entityToDto(note.getAuthor_note()))
        .animals_note(note.getAnimals_note()
            .stream().map(animal -> animalMapper.entityToDto(animal))
            .collect(Collectors.toSet()))
        .build();
  }

  @Override
  public Note dtoToEntity(NoteDto noteDto) {
    return Note.builder()
        .id(noteDto.getId())
        .noteName(noteDto.getNoteName())
        .description(noteDto.getDescription())
        .createDate(noteDto.getCreateDate())
        .comments(noteDto.getComments()
            .stream().map(commentDto -> commentMapper.dtoToEntity(commentDto))
            .collect(Collectors.toSet()))
        .author_note(userMapper.dtoToEntity(noteDto.getAuthor_note()))
        .animals_note(noteDto.getAnimals_note()
            .stream().map(animalDto -> animalMapper.dtoToEntity(animalDto))
            .collect(Collectors.toSet()))
        .build();
  }
}