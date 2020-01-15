package ru.animal.world.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.animal.world.dto.NoteDto;
import ru.animal.world.service.NoteService;

//TODO
@RestController
@RequestMapping(value = "/notes")
public class NoteController implements AbstractController<NoteDto> {

  private NoteService NoteService;

  @Autowired
  public NoteController(NoteService NoteService) {
    this.NoteService = NoteService;
  }

  @Override
  public ResponseEntity<NoteDto> create(NoteDto newNoteRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(NoteService.create(newNoteRequest));
  }

  @Override
  public ResponseEntity<NoteDto> getById(Long id) {
    return ResponseEntity.ok(NoteService.getById(id));
  }

  @Override
  public ResponseEntity<Collection<NoteDto>> getAll() {
    return ResponseEntity.ok(NoteService.getAll());
  }

  @Override
  public ResponseEntity<NoteDto> update(NoteDto updatedNoteRequest, Long id) {
    return ResponseEntity.accepted().body(NoteService.update(updatedNoteRequest, id));
  }

  @Override
  public ResponseEntity<Boolean> delete(Long id) {
    return ResponseEntity.ok().body(NoteService.delete(id));
  }
}