package com.uniandes.pruebatecnica.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Prueba Tecnica",
                description = "Aplicación de prueba tecnica para cargo de desarrollador backend Java realizada por Juan Quevedo.",
                contact = @Contact(
                        name = "Juan Angel Quevedo",
                        email = "jajquevedor@gmail.com",
                        url = "http://linkedin.com/in/jaquevedor"
                ),
                version = "1.0"
        )
)
public class SwaggerConfig {}