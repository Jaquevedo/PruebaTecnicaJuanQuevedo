package com.uniandes.pruebatecnica.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class GeneralStarshipDTO {
    
    private String nombreNave;
    private String modelo;
    private String costo;
    private String velocidad;
    
}
