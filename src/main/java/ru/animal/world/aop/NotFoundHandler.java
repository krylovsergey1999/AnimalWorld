package ru.animal.world.aop;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.animal.world.exception.NotFoundException;

@ControllerAdvice
public class NotFoundHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  protected ResponseEntity<NotFoundAdvice> userNotFoundHandler(NotFoundException e) {
    return new ResponseEntity<>(new NotFoundAdvice(e.getMessage() + " not found"), HttpStatus.NOT_FOUND);
  }

  @Data
  @AllArgsConstructor
  private static class NotFoundAdvice {

    private String message;
  }
}