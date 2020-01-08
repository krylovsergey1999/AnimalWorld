package ru.animal.world.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.animal.world.dto.UserDto;
import ru.animal.world.service.UserService;

@RestController
@RequestMapping(value = "/users")   // http://localhost:8080/users
public class UserController implements AbstractController<UserDto> {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }


  @Override
  public ResponseEntity<UserDto> create(UserDto newUserDtoRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(newUserDtoRequest));
  }

  @Override   // http://localhost:8080/users{id}
  public ResponseEntity<UserDto> getById(Long id) {
    return ResponseEntity.ok(userService.getById(id));
  }

  @Override
  public ResponseEntity<Collection<UserDto>> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }

  @Override
  public ResponseEntity<UserDto> update(UserDto updatedDtoRequest, Long id) {
    return ResponseEntity.accepted().body(userService.update(updatedDtoRequest, id));
  }

  @Override
  public ResponseEntity<Boolean> delete(Long id) {
    return ResponseEntity.ok().body(userService.delete(id));
  }
}