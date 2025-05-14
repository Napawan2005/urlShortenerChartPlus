package com.bam.urlshortenerchartplus.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bam.urlshortenerchartplus.service.UrlService;

import java.io.IOException;

@Controller
public class RedirectUrlController {

    private final UrlService UrlService;

    @Autowired
    public RedirectUrlController(UrlService UrlService) {
        this.UrlService = UrlService;
    }

    @GetMapping("/{urlCode}")
    public void redirect(
            @PathVariable("urlCode") String urlCode,
            HttpServletResponse response) throws IOException {
        String url = UrlService.getOriginalUrl(urlCode);   // ดึงต้นฉบับ
        response.sendRedirect(url);                     // ส่ง redirect
    }
}
