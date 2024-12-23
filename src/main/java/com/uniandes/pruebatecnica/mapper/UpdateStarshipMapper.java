package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.UpdateStarshipDTO;
import com.uniandes.pruebatecnica.model.StarShip;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir objetos de tipo StarShip a DTOs de tipo UpdateStarshipDTO.
 */
@Component
public class UpdateStarshipMapper {

    /**
     * Convierte un objeto StarShip en un objeto UpdateStarshipDTO.
     *
     * @param starShip El objeto StarShip a convertir.
     * @return Un objeto UpdateStarshipDTO con la información mapeada.
     */
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

        // Extrae el ID de la URL del StarShip y lo asigna al DTO
        String url = starShip.getUrl();
        String number = url.replaceAll(".*/(\\d+)/?", "$1");
        usdto.setId(number);

        return usdto;
    }

    /**
     * Convierte una lista de objetos StarShip en una lista de objetos UpdateStarshipDTO.
     *
     * @param starships Lista de objetos StarShip a convertir.
     * @return Una lista de objetos UpdateStarshipDTO con la información mapeada.
     */
    public List<UpdateStarshipDTO> toSpecificStarshipsDTO(List<StarShip> starships) {
        List<UpdateStarshipDTO> listaDTO = new ArrayList<>();

        for (StarShip starShip : starships) {
            UpdateStarshipDTO usdto = toUpdateStarshipDTO(starShip);
            listaDTO.add(usdto);
        }

        return listaDTO;
    }
}
