package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.PilotDTO;
import com.uniandes.pruebatecnica.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PilotMapper {

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

        pdto.setNombreEspecie(person.getSpecies().stream()
                .findFirst()
                .orElse("Sin especie"));

        return pdto;
    }

    public List<PilotDTO> toPilotsDTO(List<Person> people) {

        List<PilotDTO> listaDTO = new ArrayList<>();

        for (Person person : people) {
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

            pdto.setNombreEspecie(person.getSpecies().stream()
                    .findFirst()
                    .orElse("Sin especie"));

            listaDTO.add(pdto);
        }

        return listaDTO;
    }
}
