package ru.animal.world.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.animal.world.dto.CommentDto;
import ru.animal.world.entity.Comment;

@Component
public class CommentMapper extends AbstractMapper<Comment, CommentDto> {

  @Autowired
  public CommentMapper() {
    super(Comment.class, CommentDto.class);
  }
}