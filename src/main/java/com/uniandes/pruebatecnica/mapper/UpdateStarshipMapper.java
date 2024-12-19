package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.UpdateStarshipDTO;
import com.uniandes.pruebatecnica.model.StarShip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UpdateStarshipMapper {

    public UpdateStarshipDTO toUpdateStarshipDTO(StarShip starShip) {

        UpdateStarshipDTO usdto = UpdateStarshipDTO.builder()
                .nombreNave(starShip.getName())
                .modelo(starShip.getModel())
                .costo(starShip.getCost_in_credits())
                .velocidad(starShip.getMax_atmosphering_speed())
                .capacidadCargaPersonal(starShip.getCrew())
                .capacidadCargaPasajeros(starShip.getPassengers())
                .nombrePilotos(starShip.getPilots())
                .build();

        String url = starShip.getUrl();
        String number = url.replaceAll(".*/(\\d+)/?", "$1");
        usdto.setId(number);

        return usdto;
    }

    public List<UpdateStarshipDTO> toSpecificStarshipsDTO(List<StarShip> starships) {

        List<UpdateStarshipDTO> listaDTO = new ArrayList<>();

        for (StarShip starShip : starships) {
            UpdateStarshipDTO usdto = UpdateStarshipDTO.builder()
                    .nombreNave(starShip.getName())
                    .modelo(starShip.getModel())
                    .costo(starShip.getCost_in_credits())
                    .velocidad(starShip.getMax_atmosphering_speed())
                    .capacidadCargaPersonal(starShip.getCrew())
                    .capacidadCargaPasajeros(starShip.getPassengers())
                    .nombrePilotos(starShip.getPilots())
                    .build();

            String url = starShip.getUrl();
            String number = url.replaceAll(".*/(\\d+)/?", "$1");
            usdto.setId(number);
            
            listaDTO.add(usdto);
        }

        return listaDTO;
    }
}
