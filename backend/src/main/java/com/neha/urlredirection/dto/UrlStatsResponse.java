package com.neha.urlredirection.dto;

import java.time.LocalDateTime;

public class UrlStatsResponse {

    private String originalUrl;
    private String shortCode;
    private int clickCount;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean expired;

    public UrlStatsResponse(
            String originalUrl,
            String shortCode,
            int clickCount,
            LocalDateTime createdAt,
            LocalDateTime expiresAt,
            boolean expired) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.clickCount = clickCount;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.expired = expired;
    }

    // getters only (no setters needed)

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public int getClickCount() {
        return clickCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isExpired() {
        return expired;
    }
}