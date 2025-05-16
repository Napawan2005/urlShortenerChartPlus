package com.bam.urlshortenerchartplus.service;

import com.bam.urlshortenerchartplus.entity.UrlEntity;
import com.bam.urlshortenerchartplus.Repository.UrlMappingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.security.SecureRandom;

// login
@Service
public class UrlService {
    @Autowired
    private final UrlMappingRepository repository;
    @Autowired
    public UrlService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    private final SecureRandom random = new SecureRandom();

    //สร้าง http ใช้ลูกค้าใช้
    public String createShortUrl( String originalUrl, String shortUrl){
        StopWatch sw = new StopWatch();
        sw.start("requestHandling ");

        if(repository.existsByShortUrl(shortUrl)){
            throw new RuntimeException("Short url already exists");
        }
        UrlEntity mapping = new UrlEntity();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortUrl(shortUrl);
        mapping.setCreatUrl("http://localhost:8080/"+shortUrl);
        UrlEntity savedMapping = repository.save(mapping);
        sw.stop();
        long totalTimeMs = sw.getTotalTimeMillis();
        return "URL : "+ savedMapping.getCreatUrl()+"\nTime : "+(totalTimeMs/1000.0)+" ms";
    }

    // เดึง originalUrl กลับมาจาก shortCode
    public String getOriginalUrl(String shortUrl){
        var mapping = repository.findByShortUrl(shortUrl).orElseThrow(()->new RuntimeException("Short url not found"));
        return mapping.getOriginalUrl();
    }

    public String randomString(){
        int length = 6;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            String CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            sb.append(CHAR.charAt(random.nextInt(CHAR.length())));
        }
        return sb.toString();

    }

    public void saveUrl(String originalUrl, String shortUrl) {
        UrlEntity url = new UrlEntity();
        url.setOriginalUrl(originalUrl);
        if (shortUrl != null && !shortUrl.isBlank()){
            url.setShortUrl(randomString());
        }else {
            url.setShortUrl(shortUrl);
        }

        repository.save(url);
    }



}
