package org.example.productcatalogservice.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RedisTemplateConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate (){
        return new RestTemplate();
    }
}
