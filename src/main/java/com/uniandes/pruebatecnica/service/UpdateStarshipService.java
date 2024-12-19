package com.uniandes.pruebatecnica.service;

import com.uniandes.pruebatecnica.controller.dto.UpdateStarshipDTO;
import com.uniandes.pruebatecnica.exeptionshandler.NotFoundException;
import com.uniandes.pruebatecnica.mapper.UpdateStarshipMapper;
import com.uniandes.pruebatecnica.model.StarShip;
import com.uniandes.pruebatecnica.model.response.ApiNameResponse;
import com.uniandes.pruebatecnica.model.response.StarshipsResponse;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UpdateStarshipService {

    private final String urlBase = "https://swapi.py4e.com/api/starships/";
    private List<UpdateStarshipDTO> updateStarshipsDTO = new ArrayList<>();

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient webClient;
    @Autowired
    private UpdateStarshipMapper updateStarshipMapper;

    public UpdateStarshipDTO updateStarship(String id, UpdateStarshipDTO updateStarshipDTO) {
        updateStarshipDTO.setId(id);
        for (int i = 0; i < updateStarshipsDTO.size(); i++) {
            if (updateStarshipsDTO.get(i).getId().equals(id)) {
                updateStarshipsDTO.set(i, updateStarshipDTO);
                return updateStarshipDTO;
            }
        }
        throw new NotFoundException("Starship not found with id: " + id);
    }

    public UpdateStarshipDTO getStarship(String id) {
        return updateStarshipsDTO.stream()
                .filter(starship -> starship.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Starship not found with id: " + id));
    }

    @PostConstruct
    public void init() {
        String nextPageUrl = urlBase;
        List<StarShip> allStarships = new ArrayList<>();
        while (nextPageUrl != null) {
            ResponseEntity<StarshipsResponse> response = restTemplate.getForEntity(nextPageUrl, StarshipsResponse.class);
            if (response != null) {
                allStarships.addAll(response.getBody().getResults());
                nextPageUrl = response.getBody().getNext();
                continue;
            }
            break;
        }

        allStarships.stream().map(starship -> {
            if (!starship.getPilots().isEmpty()) {
                starship.setPilots(fetchNamesFromUrlsAsync(starship.getPilots()));
            }
            return starship;
        }).collect(Collectors.toList());

        updateStarshipsDTO = updateStarshipMapper.toSpecificStarshipsDTO(allStarships);
    }

    //Método para consumir una URL individual y obtener la propiedad 'name'
    private Mono<String> fetchNameFromUrlAsync(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ApiNameResponse.class)
                .map(ApiNameResponse::getName)
                .defaultIfEmpty("Unknown");
    }

    //Método para consumir una lista de URLs y obtener los nombres
    private List<String> fetchNamesFromUrlsAsync(List<String> urls) {
        return Flux.fromIterable(urls)
                .flatMap(this::fetchNameFromUrlAsync)
                .collectList()
                .block();
    }
}
