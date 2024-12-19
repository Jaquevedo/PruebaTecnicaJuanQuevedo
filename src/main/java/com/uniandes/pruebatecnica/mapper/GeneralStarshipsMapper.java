package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.GeneralStarshipDTO;
import com.uniandes.pruebatecnica.model.StarShip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GeneralStarshipsMapper {

    public GeneralStarshipDTO toGeneralStarshipDTO(StarShip starShip) {

        GeneralStarshipDTO gsdto = GeneralStarshipDTO.builder()
                .nombreNave(starShip.getName())
                .modelo(starShip.getModel())
                .costo(starShip.getCost_in_credits())
                .velocidad(starShip.getMax_atmosphering_speed())
                .build();

        return gsdto;
    }

    public List<GeneralStarshipDTO> toGeneralStarshipsDTO(List<StarShip> starships) {

        List<GeneralStarshipDTO> listaDTO = new ArrayList<>();

        for (StarShip starShip : starships) {
            GeneralStarshipDTO gsdto = GeneralStarshipDTO.builder()
                    .nombreNave(starShip.getName())
                    .modelo(starShip.getModel())
                    .costo(starShip.getCost_in_credits())
                    .velocidad(starShip.getMax_atmosphering_speed())
                    .build();
            listaDTO.add(gsdto);
        }

        return listaDTO;
    }
}
