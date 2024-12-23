package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.SpecificStarshipDTO;
import com.uniandes.pruebatecnica.model.StarShip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir objetos de tipo StarShip a DTOs de tipo SpecificStarshipDTO.
 */
@Component
public class SpecificStarshipsMapper {

    /**
     * Convierte un objeto StarShip en un objeto SpecificStarshipDTO.
     *
     * @param starShip El objeto StarShip a convertir.
     * @return Un objeto SpecificStarshipDTO con la información mapeada.
     */
    public SpecificStarshipDTO toSpecificStarshipDTO(StarShip starShip) {
        SpecificStarshipDTO ssdto = SpecificStarshipDTO.builder()
                .nombreNave(starShip.getName())
                .modelo(starShip.getModel())
                .costo(starShip.getCost_in_credits())
                .velocidad(starShip.getMax_atmosphering_speed())
                .capacidadCargaPersonal(starShip.getCrew())
                .capacidadCargaPasajeros(starShip.getPassengers())
                .build();

        return ssdto;
    }

    /**
     * Convierte una lista de objetos StarShip en una lista de objetos SpecificStarshipDTO.
     *
     * @param starships Lista de objetos StarShip a convertir.
     * @return Una lista de objetos SpecificStarshipDTO con la información mapeada.
     */
    public List<SpecificStarshipDTO> toSpecificStarshipsDTO(List<StarShip> starships) {
        List<SpecificStarshipDTO> listaDTO = new ArrayList<>();

        for (StarShip starShip : starships) {
            SpecificStarshipDTO ssdto = toSpecificStarshipDTO(starShip);
            listaDTO.add(ssdto);
        }

        return listaDTO;
    }
}
