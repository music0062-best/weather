package com.example.weather.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "kma")
@Validated
public record KmaProperties(
        @NotBlank String baseUrl,
        @NotBlank String serviceKey
) {
}
