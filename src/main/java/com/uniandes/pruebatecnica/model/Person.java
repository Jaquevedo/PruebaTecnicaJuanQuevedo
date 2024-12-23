package com.uniandes.pruebatecnica.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la información detallada de una persona o personaje, incluyendo características físicas,
 * datos de origen, y referencias a otros elementos como películas, especies, vehículos y naves.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    /**
     * El nombre del personaje.
     */
    private String name;

    /**
     * La altura del personaje en centímetros.
     */
    private String height;

    /**
     * La masa o peso del personaje en kilogramos.
     */
    private String mass;

    /**
     * El color del cabello del personaje.
     */
    private String hair_color;

    /**
     * El color de la piel del personaje.
     */
    private String skin_color;

    /**
     * El color de los ojos del personaje.
     */
    private String eye_color;

    /**
     * El año de nacimiento del personaje.
     */
    private String birth_year;

    /**
     * El género del personaje.
     */
    private String gender;

    /**
     * URL que apunta al planeta de origen del personaje.
     */
    private String homeworld;

    /**
     * Lista de URLs que apuntan a las películas en las que aparece el personaje.
     */
    private List<String> films;

    /**
     * Lista de URLs que apuntan a las especies a las que pertenece el personaje.
     */
    private List<String> species;

    /**
     * Lista de URLs que apuntan a los vehículos asociados al personaje.
     */
    private List<String> vehicles;

    /**
     * Lista de URLs que apuntan a las naves asociadas al personaje.
     */
    private List<String> starships;

    /**
     * La fecha de creación del recurso del personaje.
     */
    private String created;

    /**
     * La fecha de la última edición del recurso del personaje.
     */
    private String edited;

    /**
     * La URL única que identifica al recurso del personaje.
     */
    private String url;
}
