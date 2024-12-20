package com.mzfk.test.builder.handler.exception;

import com.mzfk.test.builder.util.exception.AccessException;
import com.mzfk.test.builder.util.exception.EntitySaveException;
import com.mzfk.test.builder.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            log.warn("Ошибка валидации поля \"{}\" : {}", fieldName, errorMessage);
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Запрашиваемый ресурс не найден", ex.getMessage());
        log.warn("{} : {}", errorResponse.description, errorResponse.error);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AccessException.class)
    public ResponseEntity<ErrorResponse> handleAccessException(AccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Отказ в доступе", ex.getMessage());
        log.error("{} : {}", errorResponse.description, errorResponse.error);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessException.class)
    public ResponseEntity<ErrorResponse> handleEntitySaveException(EntitySaveException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Отказ в доступе", ex.getMessage());
        log.error("{} : {}", errorResponse.description, errorResponse.error);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleThrowable(Throwable ex) {
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse("Произошла непредвиденная ошибка", ex.getMessage());
        log.error("{} : {}", errorResponse.description, errorResponse.error);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private String error;
        private String description;
    }
}

