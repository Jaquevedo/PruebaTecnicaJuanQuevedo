package com.uniandes.pruebatecnica.service;

import com.uniandes.pruebatecnica.controller.dto.GeneralStarshipDTO;
import com.uniandes.pruebatecnica.mapper.GeneralStarshipsMapper;
import com.uniandes.pruebatecnica.model.StarShip;
import com.uniandes.pruebatecnica.model.response.StarshipsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Servicio para gestionar operaciones relacionadas con naves espaciales generales.
 */
@Service
public class GeneralStarshipsService {

    /**
     * URL base para consumir la API de naves espaciales.
     */
    private final String urlBase = "https://swapi.py4e.com/api/starships/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GeneralStarshipsMapper generalStarshipsMapper;

    /**
     * Obtiene una nave espacial específica por su ID.
     *
     * @param id Identificador de la nave espacial.
     * @return DTO con los datos generales de la nave espacial.
     */
    public GeneralStarshipDTO getSpecificStarchip(int id) {
        ResponseEntity<StarShip> response = restTemplate.getForEntity(urlBase + id, StarShip.class);
        return generalStarshipsMapper.toGeneralStarshipDTO(response.getBody());
    }

    /**
     * Obtiene una página de naves espaciales generales.
     *
     * @param pageable Información de paginación.
     * @return Página de naves espaciales con el tamaño definido.
     */
    public Page<GeneralStarshipDTO> getGeneralStarships(Pageable pageable) {
        Pageable fixedPageable = PageRequest.of(pageable.getPageNumber(), 10); // Tamaño fijo de página
        int page = fixedPageable.getPageNumber() + 1; // Página actual
        String nextUrl = urlBase + "?page=" + page; // URL de la página
        StarshipsResponse allStarships = getAllStarships(nextUrl); // Consumo de la API

        return new PageImpl<>(
                generalStarshipsMapper.toGeneralStarshipsDTO(allStarships.getResults()),
                fixedPageable,
                allStarships.getCount());
    }

    /**
     * Obtiene los datos de una página específica de naves espaciales desde la API.
     *
     * @param nextPageUrl URL de la página a consumir.
     * @return Respuesta con los datos de la página de naves espaciales.
     */
    private StarshipsResponse getAllStarships(String nextPageUrl) {
        ResponseEntity<StarshipsResponse> response = restTemplate.getForEntity(nextPageUrl, StarshipsResponse.class);
        return response.getBody();
    }

}
