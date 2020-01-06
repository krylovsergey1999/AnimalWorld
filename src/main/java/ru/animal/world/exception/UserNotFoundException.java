package ru.animal.world.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(Long id) {
    super("Такого user нет, id: " + id);
  }
}