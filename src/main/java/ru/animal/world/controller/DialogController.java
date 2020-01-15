package ru.animal.world.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.animal.world.dto.DialogDto;
import ru.animal.world.service.DialogService;

@RestController
@RequestMapping(value = "/dialogs")
public class DialogController implements AbstractController<DialogDto> {

  private DialogService dialogService;

  @Autowired
  public DialogController(DialogService dialogService) {
    this.dialogService = dialogService;
  }

  @Override
  public ResponseEntity<DialogDto> create(DialogDto newDialogRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(dialogService.create(newDialogRequest));
  }

  @Override
  public ResponseEntity<DialogDto> getById(Long id) {
    return ResponseEntity.ok(dialogService.getById(id));
  }

  @Override
  public ResponseEntity<Collection<DialogDto>> getAll() {
    return ResponseEntity.ok(dialogService.getAll());
  }

  @Override
  public ResponseEntity<DialogDto> update(DialogDto updatedDialogRequest, Long id) {
    return ResponseEntity.accepted().body(dialogService.update(updatedDialogRequest, id));
  }

  @Override
  public ResponseEntity<Boolean> delete(Long id) {
    return ResponseEntity.ok().body(dialogService.delete(id));
  }
}