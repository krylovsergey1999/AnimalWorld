package ru.animal.world.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.animal.world.dto.NoteDto;
import ru.animal.world.dto.mapper.Mapper;
import ru.animal.world.dto.mapper.NoteMapper;
import ru.animal.world.entity.Note;
import ru.animal.world.exception.NotFoundException;
import ru.animal.world.repository.NoteRepository;
import ru.animal.world.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

  private final NoteRepository noteRepository;
  private Mapper<NoteDto, Note> noteMapper = new NoteMapper();

  @Autowired
  public NoteServiceImpl(NoteRepository NoteRepository) {
    this.noteRepository = NoteRepository;
  }

  @Override
  public NoteDto create(NoteDto newNoteDto) {
    Note result = noteRepository.save(noteMapper.dtoToEntity(newNoteDto));
    return noteMapper.entityToDto(result);
  }

  @Override
  public NoteDto getById(Long id) {
    return noteMapper.entityToDto(noteRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Note.class.getSimpleName())));
  }

  @Override
  public Collection<NoteDto> getAll() {
    return noteRepository.findAll()
        .stream().map(noteMapper::entityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public NoteDto update(NoteDto updateNoteDto, Long id) {
    return noteRepository.findById(id).map(NoteInDB -> {
      // Todo после обновления всех сущностей
      return noteMapper.entityToDto(noteRepository.saveAndFlush(NoteInDB));
    }).orElseThrow(() -> new NotFoundException(Note.class.getSimpleName()));
  }

  @Override
  public boolean delete(Long id) {
    try {
      noteRepository.deleteById(id);
      return true;
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundException(Note.class.getSimpleName());
    }
  }
}