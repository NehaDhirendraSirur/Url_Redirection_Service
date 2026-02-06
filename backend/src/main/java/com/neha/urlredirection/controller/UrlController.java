package com.neha.urlredirection.controller;

import com.neha.urlredirection.dto.CreateUrlRequest;
import com.neha.urlredirection.dto.CreateUrlResponse;
import com.neha.urlredirection.model.UrlMapping;
import com.neha.urlredirection.dto.UrlStatsResponse;
import com.neha.urlredirection.service.UrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;  

@RestController
@RequestMapping("/api/v1/urls")
public class UrlController {

    private final UrlService service;

    @Value("${app.base-url}")
    private String baseUrl;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping
    public CreateUrlResponse createShortUrl(
        @Valid @RequestBody CreateUrlRequest request) {

        UrlMapping mapping =
                service.createShortUrl(request.getOriginalUrl());

        return new CreateUrlResponse(
                baseUrl + "/r/" + mapping.getShortCode()
        );
    }

    @GetMapping("/{shortCode}/stats")
    public UrlStatsResponse getStats(@PathVariable String shortCode) {
        return service.getUrlStats(shortCode);
    }
}