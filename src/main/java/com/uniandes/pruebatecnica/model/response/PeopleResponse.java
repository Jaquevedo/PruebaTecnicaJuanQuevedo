package com.uniandes.pruebatecnica.model.response;

import com.uniandes.pruebatecnica.model.Person;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la respuesta de la API para una lista de personas.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeopleResponse {

    /**
     * Número total de elementos disponibles en la API.
     */
    private int count;

    /**
     * URL de la siguiente página de resultados (si existe).
     */
    private String next;

    /**
     * URL de la página anterior de resultados (si existe).
     */
    private String previous;

    /**
     * Lista de personas devueltas en la página actual.
     */
    private List<Person> results;
}
