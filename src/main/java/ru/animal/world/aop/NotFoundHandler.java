package ru.animal.world.aop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.animal.world.exception.NotFoundException;

import java.time.Instant;

@ControllerAdvice
public class NotFoundHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<NotFoundAdvice> userNotFoundHandler(NotFoundException e, WebRequest request) {
        return new ResponseEntity<>(new NotFoundAdvice(Instant.now(), e.getMessage() + " not found",
                request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    //Например для http://localhost:8080/animals/export/pdf , там есть DocumentException, он его обработает.
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgument(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    //для не существующих адресов
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ExceptionResponse(
                        Instant.now(),
                        "Адрес не существует",
                        request.getDescription(false)
                ),
                HttpStatus.NOT_FOUND
        );
    }


    @Data
    @AllArgsConstructor
    private static class NotFoundAdvice {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Instant timestamp;
        private String message;
        private String details;
    }

    @Data
    @AllArgsConstructor
    private static class ExceptionResponse {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Instant timestamp;
        private String message;
        private String details;
    }

    @Data
    @AllArgsConstructor
    private static class ErrorResponse {
        //TODO
    }
}
