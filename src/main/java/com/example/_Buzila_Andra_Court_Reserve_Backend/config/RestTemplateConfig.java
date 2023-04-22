package com.example._Buzila_Andra_Court_Reserve_Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig
{

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}