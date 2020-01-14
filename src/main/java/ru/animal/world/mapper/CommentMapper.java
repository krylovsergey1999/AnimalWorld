package ru.animal.world.mapper;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.CommentDto;
import ru.animal.world.entity.Comment;

@Component
public class CommentMapper extends AbstractMapper<Comment, CommentDto> {

  @Autowired
  public CommentMapper(ModelMapper mapper) {
    super(Comment.class, CommentDto.class, mapper);
  }

  @PostConstruct
  public void setupMapper() {
    modelMapper.createTypeMap(Comment.class, CommentDto.class)
        .addMappings(m -> m.skip(CommentDto::setNoteId)).setPostConverter(toDtoConverter());
    modelMapper.createTypeMap(CommentDto.class, Comment.class)
        .addMappings(m -> m.skip(Comment::setNote)).setPostConverter(toEntityConverter());
  }

  @Override
  void mapSpecificFields(Comment source, CommentDto destination) {
    destination.setNoteId(getId(source));
  }

  private Long getId(Comment source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getNote().getId();
  }
}