package com.digiplan.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.web.client.RestTemplate;

import javax.servlet.MultipartConfigElement;

@Configurable
@Component
@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("50MB"));
        factory.setMaxRequestSize(DataSize.parse("50MB"));
        return factory.createMultipartConfig();
    }
}
