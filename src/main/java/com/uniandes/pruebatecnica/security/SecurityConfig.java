package com.uniandes.pruebatecnica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Configuración de seguridad para la aplicación, basada en Spring Security.
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean que proporciona la configuración de CORS.
     */
    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    /**
     * Configura la cadena de filtros de seguridad de Spring Security.
     *
     * @param httpSecurity El objeto {@link HttpSecurity} para configurar la seguridad.
     * @return La configuración de la cadena de filtros de seguridad.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // Deshabilita la protección CSRF
                .csrf(csrf -> csrf.disable())

                // Habilita y configura CORS utilizando el bean de configuración
                .cors(cors -> cors.configurationSource(corsConfigurationSource))

                // Configura la gestión de sesiones como stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configura las reglas de autorización para las rutas
                .authorizeHttpRequests(auth -> {
                    // Permite el acceso público a las rutas relacionadas con Swagger
                    auth.requestMatchers("/swagger-ui/**").permitAll();
                    auth.requestMatchers("/v3/api-docs/**").permitAll();
                    auth.requestMatchers("/ApiDoc").permitAll();

                    // Permite el acceso público a todas las rutas del controlador
                    auth.requestMatchers("/Api/**").permitAll();

                    // Requiere autenticación para cualquier otra ruta
                    auth.anyRequest().authenticated();
                })

                // Configura la autenticación básica HTTP
                .httpBasic(withDefaults());

        // Devuelve la configuración de la cadena de filtros
        return httpSecurity.build();
    }
}
