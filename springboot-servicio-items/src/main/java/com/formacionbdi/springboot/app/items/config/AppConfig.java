package com.formacionbdi.springboot.app.items.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean("clientRestTemplate")
    @LoadBalanced
    public RestTemplate clientRestTemplate(){
        return new RestTemplate();
    }
}
