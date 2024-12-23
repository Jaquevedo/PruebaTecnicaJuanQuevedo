package com.uniandes.pruebatecnica.model.response;

import com.uniandes.pruebatecnica.model.StarShip;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Representa la respuesta de la API para una lista de naves espaciales.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StarshipsResponse {

    /**
     * Número total de naves espaciales disponibles en la API.
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
     * Lista de naves espaciales devueltas en la página actual.
     */
    private List<StarShip> results;
}
