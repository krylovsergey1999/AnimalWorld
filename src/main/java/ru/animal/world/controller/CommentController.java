package ru.animal.world.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.animal.world.dto.CommentDto;
import ru.animal.world.service.CommentService;

// TODO
@RestController
@RequestMapping(value = "/comments")
public class CommentController implements AbstractController<CommentDto> {

  private CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @Override
  public ResponseEntity<CommentDto> create(CommentDto newCommentRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(newCommentRequest));
  }

  @Override
  public ResponseEntity<CommentDto> getById(Long id) {
    return ResponseEntity.ok(commentService.getById(id));
  }

  @Override
  public ResponseEntity<Collection<CommentDto>> getAll() {
    return ResponseEntity.ok(commentService.getAll());
  }

  @Override
  public ResponseEntity<CommentDto> update(CommentDto updatedCommentRequest, Long id) {
    return ResponseEntity.accepted().body(commentService.update(updatedCommentRequest, id));
  }

  @Override
  public ResponseEntity<Boolean> delete(Long id) {
    return ResponseEntity.ok().body(commentService.delete(id));
  }
}