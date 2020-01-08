package ru.animal.world.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String nameEntity) {
    super(nameEntity);
  }
}