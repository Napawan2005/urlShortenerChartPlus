package com.bam.urlshortenerchartplus.Repository;

import com.bam.urlshortenerchartplus.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlEntity, Long> {
    boolean existsByShortUrl(String shortUrl);
    Optional<UrlEntity> findByShortUrl(String shortUrl);
}
