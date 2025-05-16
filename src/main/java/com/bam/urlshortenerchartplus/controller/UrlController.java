package com.bam.urlshortenerchartplus.controller;

import com.bam.urlshortenerchartplus.dto.UrlResponse;
import com.bam.urlshortenerchartplus.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UrlController {
    @Autowired
    private final UrlService urlService;
    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }
    //defualt api
    @GetMapping
    public String home(){
        return "Hello World";
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@Valid @RequestBody UrlResponse request) {

        if (request.getShortUrl() == null || request.getShortUrl().isBlank()) {
            request.setShortUrl(urlService.randomString());
        }


        String fullShortLink = urlService.createShortUrl(
                request.getOriginalUrl(),
                request.getShortUrl()
        );
        urlService.saveUrl(request.getOriginalUrl(), request.getShortUrl());

        return ResponseEntity.ok(fullShortLink);
    }

}



