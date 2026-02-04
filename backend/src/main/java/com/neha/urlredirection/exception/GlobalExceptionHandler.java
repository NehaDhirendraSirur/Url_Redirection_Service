package com.neha.urlredirection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(UrlNotFoundException ex) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage()
        );
    }

    @ExceptionHandler(UrlExpiredException.class)
    @ResponseStatus(HttpStatus.GONE)
    public Map<String, Object> handleExpired(UrlExpiredException ex) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 410,
                "error", "Gone",
                "message", ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
          .getFieldErrors()
          .forEach(error ->
              errors.put(error.getField(), error.getDefaultMessage())
          );

        response.put("status", 400);
        response.put("errors", errors);
        response.put("timestamp", LocalDateTime.now());

        return response;
    }
}