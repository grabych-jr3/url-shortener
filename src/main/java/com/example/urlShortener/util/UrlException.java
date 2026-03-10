package com.example.urlShortener.util;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public class UrlException {
    private String message;
    private Instant timestamp;
    private HttpStatus httpStatus;

    public UrlException(String message, Instant timestamp, HttpStatus httpStatus) {
        this.message = message;
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
