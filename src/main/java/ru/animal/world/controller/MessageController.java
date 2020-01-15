package ru.animal.world.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.animal.world.dto.MessageDto;
import ru.animal.world.service.MessageService;

// TODO
@RestController
@RequestMapping(value = "/messages")
public class MessageController implements AbstractController<MessageDto> {

  private MessageService MessageService;

  @Autowired
  public MessageController(MessageService MessageService) {
    this.MessageService = MessageService;
  }

  @Override
  public ResponseEntity<MessageDto> create(MessageDto newMessageRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(MessageService.create(newMessageRequest));
  }

  @Override
  public ResponseEntity<MessageDto> getById(Long id) {
    return ResponseEntity.ok(MessageService.getById(id));
  }

  @Override
  public ResponseEntity<Collection<MessageDto>> getAll() {
    return ResponseEntity.ok(MessageService.getAll());
  }

  @Override
  public ResponseEntity<MessageDto> update(MessageDto updatedMessageRequest, Long id) {
    return ResponseEntity.accepted().body(MessageService.update(updatedMessageRequest, id));
  }

  @Override
  public ResponseEntity<Boolean> delete(Long id) {
    return ResponseEntity.ok().body(MessageService.delete(id));
  }
}