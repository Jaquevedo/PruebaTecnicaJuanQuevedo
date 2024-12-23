package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.GeneralStarshipDTO;
import com.uniandes.pruebatecnica.model.StarShip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entidades de tipo StarShip a DTOs de tipo GeneralStarshipDTO.
 */
@Component
public class GeneralStarshipsMapper {

    /**
     * Convierte un objeto de tipo StarShip a un objeto de tipo GeneralStarshipDTO.
     *
     * @param starShip Objeto de tipo StarShip a convertir.
     * @return Un objeto de tipo GeneralStarshipDTO que contiene la información mapeada.
     */
    public GeneralStarshipDTO toGeneralStarshipDTO(StarShip starShip) {
        return GeneralStarshipDTO.builder()
                .nombreNave(starShip.getName())
                .modelo(starShip.getModel())
                .costo(starShip.getCost_in_credits())
                .velocidad(starShip.getMax_atmosphering_speed())
                .build();
    }

    /**
     * Convierte una lista de objetos de tipo StarShip a una lista de objetos de tipo GeneralStarshipDTO.
     *
     * @param starships Lista de objetos StarShip a convertir.
     * @return Una lista de objetos GeneralStarshipDTO con la información mapeada.
     */
    public List<GeneralStarshipDTO> toGeneralStarshipsDTO(List<StarShip> starships) {
        List<GeneralStarshipDTO> listaDTO = new ArrayList<>();
        for (StarShip starShip : starships) {
            GeneralStarshipDTO gsdto = toGeneralStarshipDTO(starShip);
            listaDTO.add(gsdto);
        }
        return listaDTO;
    }
}
