package com.bam.urlshortenerchartplus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "url_shortener")
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true , unique = true)
    private String shortUrl;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = true)
    private String creatUrl;
}
