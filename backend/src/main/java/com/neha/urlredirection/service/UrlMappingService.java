package com.neha.urlredirection.service;

import com.neha.urlredirection.exception.UrlExpiredException;
import com.neha.urlredirection.exception.UrlNotFoundException;
import com.neha.urlredirection.dto.UrlStatsResponse;
import com.neha.urlredirection.model.UrlMapping;
import com.neha.urlredirection.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UrlMappingService {

    private final UrlMappingRepository repository;

    public UrlMappingService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    // ---------------- CREATE ----------------
    public UrlMapping createShortUrl(String originalUrl) {
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortCode(generateUniqueShortCode());
        mapping.setClickCount(0);
        mapping.setCreatedAt(LocalDateTime.now());
        mapping.setExpiresAt(LocalDateTime.now().plusDays(30));

        return repository.save(mapping);
    }

    // ---------------- REDIRECT + TRACK ----------------
    public UrlMapping getAndTrackValidUrl(String shortCode) {
        UrlMapping mapping = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found"));

        // expiry check (safe even if null)
        if (mapping.getExpiresAt() != null &&
                mapping.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new UrlExpiredException("Short URL has expired");
        }

        // safe increment
        mapping.setClickCount(
                mapping.getClickCount() == null ? 1 : mapping.getClickCount() + 1);

        return repository.save(mapping);
    }

    // ---------------- UTILITY ----------------
    private String generateUniqueShortCode() {
        String code;
        do {
            code = UUID.randomUUID().toString().substring(0, 6);
        } while (repository.findByShortCode(code).isPresent());
        return code;
    }

    // ---------------- STATS ----------------

    public UrlStatsResponse getUrlStats(String shortCode) {
    UrlMapping mapping = repository.findByShortCode(shortCode)
            .orElseThrow(() -> new UrlNotFoundException("Short URL not found"));

    boolean expired =
            mapping.getExpiresAt() != null &&
            mapping.getExpiresAt().isBefore(LocalDateTime.now());

    return new UrlStatsResponse(
            mapping.getOriginalUrl(),
            mapping.getShortCode(),
            mapping.getClickCount(),
            mapping.getCreatedAt(),
            mapping.getExpiresAt(),
            expired
    );
}
}