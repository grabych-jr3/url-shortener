package com.example.urlShortener.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class UrlDTOWithoutStats {
    private int id;

    @NotBlank(message = "Url can not be empty")
    private String url;

    @Size(min = 1, message = "Short code must contain at least 1 symbol")
    private String shortCode;

    private Instant createdAt;

    private Instant updatedAt;

    public UrlDTOWithoutStats() {
    }

    public UrlDTOWithoutStats(int id, String url, String shortCode, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.url = url;
        this.shortCode = shortCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
