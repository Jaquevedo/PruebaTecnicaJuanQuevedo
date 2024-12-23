package com.uniandes.pruebatecnica.service;

import com.uniandes.pruebatecnica.mapper.SpecificStarshipsMapper;
import com.uniandes.pruebatecnica.controller.dto.SpecificStarshipDTO;
import com.uniandes.pruebatecnica.model.StarShip;
import com.uniandes.pruebatecnica.model.response.StarshipsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con naves específicas.
 */
@Service
public class SpecificStarshipsSevice {

    /**
     * URL base para interactuar con la API de naves.
     */
    private final String urlBase = "https://swapi.py4e.com/api/starships/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SpecificStarshipsMapper specificStarshipsMapper;

    /**
     * Obtiene la información detallada de una nave específica a partir de su ID.
     *
     * @param id Identificador de la nave.
     * @return DTO con los datos de la nave específica.
     */
    public SpecificStarshipDTO getSpecificStarchip(int id) {
        ResponseEntity<StarShip> response = restTemplate.getForEntity(urlBase + id, StarShip.class);
        return specificStarshipsMapper.toSpecificStarshipDTO(response.getBody());
    }

    /**
     * Obtiene una página de naves específicas en formato DTO.
     *
     * @param pageable Información de paginación.
     * @return Página de naves específicas.
     */
    public Page<SpecificStarshipDTO> getSpecificStarships(Pageable pageable) {
        Pageable fixedPageable = PageRequest.of(pageable.getPageNumber(), 10);
        int page = fixedPageable.getPageNumber() + 1;
        String nextUrl = urlBase + "?page=" + page;
        StarshipsResponse allStarships = getAllStarships(nextUrl);

        return new PageImpl<>(
                specificStarshipsMapper.toSpecificStarshipsDTO(allStarships.getResults()),
                fixedPageable,
                allStarships.getCount());
    }

    /**
     * Consume la API para obtener una página específica de naves espaciales.
     *
     * @param nextPageUrl URL de la página a consumir.
     * @return Respuesta con los datos de las naves espaciales.
     */
    private StarshipsResponse getAllStarships(String nextPageUrl) {
        ResponseEntity<StarshipsResponse> response = restTemplate.getForEntity(nextPageUrl, StarshipsResponse.class);
        return response.getBody();
    }
}
