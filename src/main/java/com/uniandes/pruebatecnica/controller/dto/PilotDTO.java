package com.uniandes.pruebatecnica.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO que representa la información de un piloto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PilotDTO {

    /**
     * El nombre del piloto.
     */
    private String nombre;

    /**
     * La altura del piloto en centímetros.
     */
    private String altura;

    /**
     * El género del piloto (e.g., masculino, femenino, etc.).
     */
    private String genero;

    /**
     * El peso del piloto en kilogramos.
     */
    private String peso;

    /**
     * El año de nacimiento del piloto (formato e.g., 19BBY).
     */
    private String añoNacimiento;

    /**
     * El nombre de la especie del piloto.
     */
    private String nombreEspecie;

    /**
     * Lista con los nombres de los vehículos asociados al piloto.
     */
    private List<String> nombreVehiculos;

    /**
     * Lista con los nombres de las naves espaciales asociadas al piloto.
     */
    private List<String> nombreNaves;

    /**
     * El nombre del planeta de origen del piloto.
     */
    private String nombrePlanetaOrigen;
}

