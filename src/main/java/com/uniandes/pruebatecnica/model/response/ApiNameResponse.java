package com.uniandes.pruebatecnica.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la respuesta de la API que contiene únicamente el nombre asociado a un recurso.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiNameResponse {

    /**
     * El nombre del recurso proporcionado por la API.
     */
    private String name;
}
