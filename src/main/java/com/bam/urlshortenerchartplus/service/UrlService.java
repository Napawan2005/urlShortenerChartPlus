package com.bam.urlshortenerchartplus.service;

import com.bam.urlshortenerchartplus.model.UrlMapping;
import com.bam.urlshortenerchartplus.model.UrlMappingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

// login
@Service
public class UrlService {
    @Autowired
    private final UrlMappingRepository repository;
    @Autowired
    public UrlService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    //สร้าง http ใช้ลูกค้าใช้
    public String createShortUrl( String originalUrl, String shortUrl){
        StopWatch sw = new StopWatch();
        sw.start("requestHandling ");

        if(repository.existsByShortUrl(shortUrl)){
            throw new RuntimeException("Short url already exists");
        }
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortUrl(shortUrl);
        mapping.setCreatUrl("http://localhost:8080/"+shortUrl);
        UrlMapping savedMapping = repository.save(mapping);
        sw.stop();
        long totalTimeMs = sw.getTotalTimeMillis();
        return "URL : "+ savedMapping.getCreatUrl()+"\nTime : "+(totalTimeMs/1000.0)+" ms";
    }

    // เดึง originalUrl กลับมาจาก shortCode
    public String getOriginalUrl(String shortUrl){
        var mapping = repository.findByShortUrl(shortUrl).orElseThrow(()->new RuntimeException("Short url not found"));
        return mapping.getOriginalUrl();
    }


}
