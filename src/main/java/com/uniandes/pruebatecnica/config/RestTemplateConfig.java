package com.uniandes.pruebatecnica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Clase de configuración para proporcionar un bean de tipo RestTemplate.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Crea y registra un bean de tipo RestTemplate.
     * 
     * @return una instancia nueva de RestTemplate.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
