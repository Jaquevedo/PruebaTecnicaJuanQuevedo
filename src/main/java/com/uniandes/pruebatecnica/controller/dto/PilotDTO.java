package com.uniandes.pruebatecnica.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PilotDTO {

    private String nombre;
    private String altura;
    private String genero;
    private String peso;
    private String añoNacimiento;
    private String nombreEspecie;
    private List<String> nombreVehiculos;
    private List<String> nombreNaves;
    private String nombrePlanetaOrigen;

}
