package com.example.urlShortener.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ApiHandlerException {

    @ExceptionHandler(value = {UrlNotFoundException.class})
    public ResponseEntity<UrlException> handleUrlNotFoundException(UrlNotFoundException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        UrlException urlException = new UrlException(
                e.getMessage(),
                Instant.now(),
                status
        );

        return new ResponseEntity<>(urlException, status);
    }

    @ExceptionHandler(value = {UrlNotCreatedException.class})
    public ResponseEntity<UrlException> handleUrlNotCretedException(UrlNotCreatedException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        UrlException urlException = new UrlException(
                e.getMessage(),
                Instant.now(),
                status
        );

        return new ResponseEntity<>(urlException, status);
    }
}
