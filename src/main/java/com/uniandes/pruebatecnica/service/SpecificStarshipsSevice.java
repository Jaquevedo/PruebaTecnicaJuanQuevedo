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

@Service
public class SpecificStarshipsSevice {
    
    private final String urlBase = "https://swapi.py4e.com/api/starships/";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SpecificStarshipsMapper specificStarshipsMapper;

    public SpecificStarshipDTO getSpecificStarchip (int id){
        ResponseEntity<StarShip> response = restTemplate.getForEntity(urlBase + id, StarShip.class);
        return specificStarshipsMapper.toSpecificStarshipDTO(response.getBody());
    }
    
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

    //Metodo para consumir la pagina actual de la Api
    private StarshipsResponse getAllStarships(String nextPageUrl) {
        ResponseEntity<StarshipsResponse> response = restTemplate.getForEntity(nextPageUrl, StarshipsResponse.class);
        return response.getBody();
    }
}
