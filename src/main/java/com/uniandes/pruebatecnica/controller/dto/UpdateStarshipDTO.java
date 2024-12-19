package com.uniandes.pruebatecnica.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdateStarshipDTO {
    
    @JsonIgnore
    private String id;
    private String nombreNave;
    private String modelo;
    private String costo;
    private String velocidad;
    private String capacidadCargaPersonal;
    private String capacidadCargaPasajeros;
    @Builder.Default
    private List<String> nombrePilotos = new ArrayList<>(); 
    
}
