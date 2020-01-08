package ru.animal.world.dto.mapper;

import ru.animal.world.dto.CommentDto;
import ru.animal.world.entity.Comment;

// Todo после обновления всех сущностей
public class CommentMapper implements Mapper<CommentDto, Comment> {

  @Override
  public CommentDto entityToDto(Comment comment) {
    return null;
  }

  @Override
  public Comment dtoToEntity(CommentDto commentDto) {
    return null;
  }
}
