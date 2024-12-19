package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.SpecificStarshipDTO;
import com.uniandes.pruebatecnica.model.StarShip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SpecificStarshipsMapper {

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

    public List<SpecificStarshipDTO> toSpecificStarshipsDTO(List<StarShip> starships) {

        List<SpecificStarshipDTO> listaDTO = new ArrayList<>();

        for (StarShip starShip : starships) {
            SpecificStarshipDTO ssdto = SpecificStarshipDTO.builder()
                    .nombreNave(starShip.getName())
                    .modelo(starShip.getModel())
                    .costo(starShip.getCost_in_credits())
                    .velocidad(starShip.getMax_atmosphering_speed())
                    .capacidadCargaPersonal(starShip.getCrew())
                    .capacidadCargaPasajeros(starShip.getPassengers())
                    .build();
            listaDTO.add(ssdto);
        }

        return listaDTO;
    }
}
