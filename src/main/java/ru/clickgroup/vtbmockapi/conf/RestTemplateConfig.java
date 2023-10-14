package ru.clickgroup.vtbmockapi.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate RestTemplateCreator(){
        return new RestTemplate();
    }
}
