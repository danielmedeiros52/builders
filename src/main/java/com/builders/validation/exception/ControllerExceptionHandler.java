package com.builders.validation.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler {

  public static ResponseEntity<StandardError> customizeErrors(BuildersException systemErrorMessages) {

    return ResponseEntity.status(systemErrorMessages.getStatusCode()).body(
        new ValidationError(systemErrorMessages.getStatusCode(), "Validation Error", System.currentTimeMillis(),
            systemErrorMessages.getFlow(), systemErrorMessages.getMessage()));


  }

  @ExceptionHandler(MethodArgumentNotValidException.class)

  public ResponseEntity<StandardError> customizeValidationErrors(MethodArgumentNotValidException error) {
    ValidationError validationError = new ValidationError(HttpStatus.NOT_FOUND.value(), "Validation Error", System.currentTimeMillis());
    for (FieldError fieldErrorObject : error.getBindingResult().getFieldErrors()) {
      validationError.addError(fieldErrorObject.getField(), fieldErrorObject.getDefaultMessage());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
  }
}