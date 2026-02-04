package com.neha.urlredirection.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public class CreateUrlRequest {

    @NotBlank(message = "Original URL cannot be empty")
    @URL(message = "Invalid URL format")
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}