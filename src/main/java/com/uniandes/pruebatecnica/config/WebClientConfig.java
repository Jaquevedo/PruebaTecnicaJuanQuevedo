package com.uniandes.pruebatecnica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Clase de configuración para crear un bean de WebClient.
 */
@Configuration
public class WebClientConfig {

    /**
     * Define un bean para realizar solicitudes HTTP no bloqueantes con WebClient.
     * 
     * @param builder el constructor de WebClient.
     * @return una instancia configurada de WebClient.
     */
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
