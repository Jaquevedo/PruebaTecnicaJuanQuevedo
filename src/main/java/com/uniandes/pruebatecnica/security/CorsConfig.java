package com.uniandes.pruebatecnica.security;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuración para habilitar CORS en la aplicación.
 */
@Configuration
public class CorsConfig {

    /**
     * Configura y define las reglas de CORS para la aplicación.
     *
     * @return Una instancia de {@link CorsConfigurationSource} que contiene las reglas de CORS.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Crear configuración CORS
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Permitir solicitudes de cualquier origen
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));

        // Permitir métodos HTTP específicos
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        // Permitir todos los encabezados en las solicitudes
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        // Asignar configuración a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
