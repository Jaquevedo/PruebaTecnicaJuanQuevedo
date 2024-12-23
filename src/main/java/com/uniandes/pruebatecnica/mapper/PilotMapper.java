package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.PilotDTO;
import com.uniandes.pruebatecnica.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entidades de tipo Person a DTOs de tipo PilotDTO.
 */
@Component
public class PilotMapper {

    /**
     * Convierte un objeto de tipo Person a un objeto de tipo PilotDTO.
     *
     * @param person Objeto de tipo Person a convertir.
     * @return Un objeto de tipo PilotDTO con la información mapeada.
     */
    public PilotDTO toPilotDTO(Person person) {
        PilotDTO pdto = PilotDTO.builder()
                .nombre(person.getName())
                .altura(person.getHeight())
                .genero(person.getGender())
                .peso(person.getMass())
                .añoNacimiento(person.getBirth_year())
                .nombrePlanetaOrigen(person.getHomeworld())
                .nombreVehiculos(person.getVehicles())
                .nombreNaves(person.getStarships())
                .build();

        // Configura la especie si está presente, de lo contrario establece "Sin especie"
        pdto.setNombreEspecie(person.getSpecies().stream()
                .findFirst()
                .orElse("Sin especie"));

        return pdto;
    }

    /**
     * Convierte una lista de objetos de tipo Person a una lista de objetos de tipo PilotDTO.
     *
     * @param people Lista de objetos Person a convertir.
     * @return Una lista de objetos PilotDTO con la información mapeada.
     */
    public List<PilotDTO> toPilotsDTO(List<Person> people) {
        List<PilotDTO> listaDTO = new ArrayList<>();

        for (Person person : people) {
            PilotDTO pdto = toPilotDTO(person);
            listaDTO.add(pdto);
        }

        return listaDTO;
    }
}
