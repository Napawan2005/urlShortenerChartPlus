package com.bam.urlshortenerchartplus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UrlResponse {

    @NotBlank(message = "Original URL is required")
    @Pattern(
            regexp = "^(https?://)([\\w.-]+)(:[0-9]+)?(/.*)?$",
            message = "Invalid URL format. Must start with http:// or https:// and be a valid URL"
    )
    private String originalUrl;

    // optional: ผู้ใช้จะป้อน short URL เองก็ได้
    private String shortUrl;
}
