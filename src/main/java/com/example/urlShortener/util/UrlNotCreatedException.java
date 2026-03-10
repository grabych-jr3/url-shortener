package com.example.urlShortener.util;

public class UrlNotCreatedException extends RuntimeException {
    public UrlNotCreatedException(String message) {
        super(message);
    }
}
