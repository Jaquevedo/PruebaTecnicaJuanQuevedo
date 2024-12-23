package com.uniandes.pruebatecnica.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa información específica de una nave espacial.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecificStarshipDTO {

    /**
     * El identificador único de la nave espacial.
     */
    private int id;

    /**
     * El nombre de la nave espacial.
     */
    private String nombreNave;

    /**
     * El modelo de la nave espacial.
     */
    private String modelo;

    /**
     * El costo de la nave espacial en créditos.
     */
    private String costo;

    /**
     * La velocidad máxima de la nave espacial.
     */
    private String velocidad;

    /**
     * La capacidad de carga de personal de la nave.
     */
    private String capacidadCargaPersonal;

    /**
     * La capacidad de carga de pasajeros de la nave.
     */
    private String capacidadCargaPasajeros;
}
