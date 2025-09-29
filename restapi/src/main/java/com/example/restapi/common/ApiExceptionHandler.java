package com.example.restapi.common;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<?> handle(RuntimeException ex) {
    int code = switch (ex.getMessage()) {
      case "Forbidden" -> 403;
      case "Not found" -> 404;
      case "Username already exists" -> 409;
      default -> 400;
    };
    return ResponseEntity.status(code).body(new ErrorBody(ex.getMessage()));
  }
  record ErrorBody(String message) {}
}
