package com.uniandes.pruebatecnica.model;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Representa la información detallada de una nave espacial, incluyendo características técnicas,
 * capacidades y relaciones con otros recursos como pilotos y películas.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StarShip {

    /**
     * El nombre de la nave espacial.
     */
    private String name;

    /**
     * El modelo de la nave espacial.
     */
    private String model;

    /**
     * El fabricante de la nave espacial.
     */
    private String manufacturer;

    /**
     * El costo de la nave espacial en créditos.
     */
    private String cost_in_credits;

    /**
     * La longitud de la nave espacial en metros.
     */
    private String length;

    /**
     * La velocidad máxima de la nave espacial en la atmósfera.
     */
    private String max_atmosphering_speed;

    /**
     * La cantidad de tripulación requerida para operar la nave.
     */
    private String crew;

    /**
     * La capacidad máxima de pasajeros de la nave.
     */
    private String passengers;

    /**
     * La capacidad de carga de la nave en kilogramos.
     */
    private String cargo_capacity;

    /**
     * El suministro de consumibles que la nave puede llevar (ejemplo: 2 months).
     */
    private String consumables;

    /**
     * La calificación del hiperimpulsor de la nave.
     */
    private String hyperdrive_rating;

    /**
     * La distancia que puede viajar la nave.
     */
    private String MGLT;

    /**
     * La clase de la nave espacial.
     */
    private String starship_class;

    /**
     * Lista de URLs que apuntan a los pilotos asociados con la nave.
     */
    private List<String> pilots;

    /**
     * Lista de URLs que apuntan a las películas en las que aparece la nave.
     */
    private List<String> films;

    /**
     * La fecha de creación del recurso de la nave.
     */
    private String created;

    /**
     * La fecha de la última edición del recurso de la nave.
     */
    private String edited;

    /**
     * La URL única que identifica el recurso de la nave.
     */
    private String url;
}
