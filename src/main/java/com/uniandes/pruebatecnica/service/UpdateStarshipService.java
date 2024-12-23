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

/**
 * Servicio para gestionar las actualizaciones y obtención de naves espaciales.
 * Proporciona métodos para modificar y consultar información localmente almacenada.
 */
@Service
public class UpdateStarshipService {

    /**
     * URL base para consumir la API de naves espaciales.
     */
    private final String urlBase = "https://swapi.py4e.com/api/starships/";

    /**
     * Lista de naves espaciales actualizadas mantenida en memoria.
     */
    private List<UpdateStarshipDTO> updateStarshipsDTO = new ArrayList<>();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private UpdateStarshipMapper updateStarshipMapper;

    /**
     * Actualiza una nave espacial en la lista local.
     *
     * @param id ID de la nave espacial a actualizar.
     * @param updateStarshipDTO Objeto con la nueva información de la nave.
     * @return DTO actualizado de la nave espacial.
     * @throws NotFoundException Si no se encuentra una nave con el ID proporcionado.
     */
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

    /**
     * Obtiene una nave espacial de la lista local por su ID.
     *
     * @param id ID de la nave espacial.
     * @return DTO de la nave espacial encontrada.
     * @throws NotFoundException Si no se encuentra una nave con el ID proporcionado.
     */
    public UpdateStarshipDTO getStarship(String id) {
        return updateStarshipsDTO.stream()
                .filter(starship -> starship.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Starship not found with id: " + id));
    }

    /**
     * Inicializa la lista local de naves espaciales consumiendo datos de la API.
     * Los datos son procesados y almacenados en memoria.
     */
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

    /**
     * Consume una URL individual para obtener el valor de la propiedad 'name'.
     *
     * @param url URL a consumir.
     * @return Nombre obtenido desde la URL.
     */
    private Mono<String> fetchNameFromUrlAsync(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ApiNameResponse.class)
                .map(ApiNameResponse::getName)
                .defaultIfEmpty("Unknown");
    }

    /**
     * Consume una lista de URLs para obtener sus respectivos nombres.
     *
     * @param urls Lista de URLs a consumir.
     * @return Lista de nombres obtenidos desde las URLs.
     */
    private List<String> fetchNamesFromUrlsAsync(List<String> urls) {
        return Flux.fromIterable(urls)
                .flatMap(this::fetchNameFromUrlAsync)
                .collectList()
                .block();
    }
}
