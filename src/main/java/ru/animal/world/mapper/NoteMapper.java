package ru.animal.world.mapper;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.NoteDto;
import ru.animal.world.entity.Note;
import ru.animal.world.repository.UserRepository;

@Component
public class NoteMapper extends AbstractMapper<Note, NoteDto> {

  private UserRepository userRepository;

  @Autowired
  public NoteMapper(ModelMapper mapper, UserRepository userRepository) {
    super(Note.class, NoteDto.class, mapper);
    this.userRepository = userRepository;
  }

  @PostConstruct
  public void setupMapper() {
    modelMapper.createTypeMap(Note.class, NoteDto.class)
        .addMappings(m -> m.skip(NoteDto::setAuthorId)).setPostConverter(toDtoConverter());
    modelMapper.createTypeMap(NoteDto.class, Note.class)
        .addMappings(m -> m.skip(Note::setAuthorNote)).setPostConverter(toEntityConverter());
  }

  @Override
  void mapSpecificFields(Note source, NoteDto destination) {
    destination.setAuthorId(getId(source));
  }

  private Long getId(Note source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAuthorNote().getId();
  }

  @Override
  void mapSpecificFields(NoteDto source, Note destination) {
    destination.setAuthorNote(userRepository.findById(source.getAuthorId()).orElse(null));
  }
}