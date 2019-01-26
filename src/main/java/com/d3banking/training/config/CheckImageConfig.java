package com.d3banking.training.config;

import org.springframework.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.d3banking.training.controller", "com.d3banking.training.service"})
@ConfigurationProperties("com.d3banking")
@EnableConfigurationProperties(CheckImageConfig.class)
@EnableCaching
public class CheckImageConfig {

    private String checkImageCacheSpec ="initialCapacity=512,maximumSize=512,expireAfterAccess=1h";

    @Bean
    public Cache accountProductAttributeCache()
    {
        return new CaffeineCache("check-image-cache",
                Caffeine.from(CaffeineSpec.parse(checkImageCacheSpec)).build()
        );
    }


}