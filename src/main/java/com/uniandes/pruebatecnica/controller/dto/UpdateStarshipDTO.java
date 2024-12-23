package com.uniandes.pruebatecnica.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO que representa la información necesaria para actualizar una nave espacial.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStarshipDTO {

    /**
     * Identificador único de la nave espacial.
     * Este campo será ignorado en la serialización/deserialización JSON.
     */
    @JsonIgnore
    private String id;

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
     * La capacidad de carga personal de la nave espacial.
     */
    private String capacidadCargaPersonal;

    /**
     * La capacidad de carga de pasajeros de la nave espacial.
     */
    private String capacidadCargaPasajeros;

    /**
     * Lista de nombres de los pilotos asociados con la nave espacial.
     * Esta lista se inicializa por defecto como una nueva lista vacía.
     */
    @Builder.Default
    private List<String> nombrePilotos = new ArrayList<>();
}

