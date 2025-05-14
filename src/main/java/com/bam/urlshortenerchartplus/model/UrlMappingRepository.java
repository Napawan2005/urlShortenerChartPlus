package com.bam.urlshortenerchartplus.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    boolean existsByShortUrl(String shortUrl);
    Optional<UrlMapping> findByShortUrl(String shortUrl);
}
