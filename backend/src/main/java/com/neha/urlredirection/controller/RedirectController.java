package com.neha.urlredirection.controller;

import com.neha.urlredirection.model.UrlMapping;
import com.neha.urlredirection.service.UrlMappingService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/r")
public class RedirectController {

    private final UrlMappingService service;

    public RedirectController(UrlMappingService service) {
        this.service = service;
    }

    @GetMapping("/{shortCode}")
    public void redirect(
            @PathVariable String shortCode,
            HttpServletResponse response
    ) throws IOException {

        UrlMapping mapping = service.getAndTrackValidUrl(shortCode);

        response.setStatus(HttpServletResponse.SC_FOUND); // 302
        response.sendRedirect(mapping.getOriginalUrl());
    }
}