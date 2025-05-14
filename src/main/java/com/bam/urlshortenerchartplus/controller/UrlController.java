package com.bam.urlshortenerchartplus.controller;

import com.bam.urlshortenerchartplus.model.UrlMapping;
import com.bam.urlshortenerchartplus.service.UrlService;
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
    public ResponseEntity<String> shortenUrl(@RequestBody UrlMapping urlMapping){
        String  fullShortLink  = urlService.createShortUrl(
                urlMapping.getOriginalUrl(),
                urlMapping.getShortUrl());
       return ResponseEntity.ok(fullShortLink );
    }


}
