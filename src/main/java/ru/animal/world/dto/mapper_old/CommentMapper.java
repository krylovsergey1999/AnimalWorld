package ru.animal.world.dto.mapper_old;

import ru.animal.world.dto.CommentDto;
import ru.animal.world.entity.Comment;

public class CommentMapper implements Mapper<CommentDto, Comment> {

  private NoteMapper noteMapper = new NoteMapper();

  @Override
  public CommentDto entityToDto(Comment comment) {
    return CommentDto.builder()
        .id(comment.getId())
        .commentText(comment.getCommentText())
        .commentTime(comment.getCommentTime())
        .note(noteMapper.entityToDto(comment.getNote()))
        .build();
  }

  @Override
  public Comment dtoToEntity(CommentDto commentDto) {
    return Comment.builder()
        .id(commentDto.getId())
        .commentText(commentDto.getCommentText())
        .commentTime(commentDto.getCommentTime())
        .note(noteMapper.dtoToEntity(commentDto.getNote()))
        .build();
  }
}