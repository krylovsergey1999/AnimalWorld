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

    /**
     * Обратботка 500 ошибок.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * есть такой тип ошибок при запросе(например в случае при users/qwe вместо users/123, звучит примерно так "MethodArgumentTypeMismatchException ... cannot parse String to Integer"
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgument(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    /**
     * Обработчик запросов на не существующие URL.
     * В application.yml добавлен проперти для работы метода.
     */
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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
        private Instant timestamp;
        private String message;
        private String details;
    }

    @Data
    @AllArgsConstructor
    private static class ExceptionResponse {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
        private Instant timestamp;
        private String message;
        private String details;
    }
}
