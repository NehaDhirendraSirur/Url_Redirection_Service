package com.neha.urlredirection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.GONE) // 410
    @ExceptionHandler(UrlExpiredException.class)
    public void handleExpiredUrl() {}

    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(UrlNotFoundException.class)
    public void handleNotFound() {}
}