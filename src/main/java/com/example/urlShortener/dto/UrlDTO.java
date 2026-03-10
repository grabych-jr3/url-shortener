package com.example.urlShortener.dto;

import jakarta.validation.constraints.NotBlank;

public class UrlDTO {

    @NotBlank(message = "Url can not be empty")
    private String url;

    public UrlDTO() {
    }

    public UrlDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
