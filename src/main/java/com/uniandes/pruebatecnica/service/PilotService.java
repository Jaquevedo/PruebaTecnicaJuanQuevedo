package com.uniandes.pruebatecnica.service;

import com.uniandes.pruebatecnica.model.response.ApiNameResponse;
import com.uniandes.pruebatecnica.controller.dto.PilotDTO;
import com.uniandes.pruebatecnica.mapper.PilotMapper;
import com.uniandes.pruebatecnica.model.Person;
import com.uniandes.pruebatecnica.model.response.PeopleResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio encargado de gestionar operaciones relacionadas con los pilotos.
 */
@Service
public class PilotService {

    /**
     * URL base para interactuar con la API de pilotos.
     */
    private final String urlBase = "https://swapi.py4e.com/api/people/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PilotMapper pilotMapper;

    @Autowired
    private WebClient webClient;

    /**
     * Obtiene la información de un piloto específico a partir de su ID.
     *
     * @param id Identificador del piloto.
     * @return DTO con los datos del piloto.
     */
    public PilotDTO getPilot(int id) {
        ResponseEntity<Person> response = restTemplate.getForEntity(urlBase + id, Person.class);
        return pilotMapper.toPilotDTO(replaceUrlWithNames(response.getBody()));
    }

    /**
     * Reemplaza las URLs de propiedades asociadas al piloto por sus nombres.
     *
     * @param person Objeto del piloto.
     * @return Objeto del piloto con las URLs reemplazadas por nombres.
     */
    private Person replaceUrlWithNames(Person person) {
        if (person.getSpecies() != null && !person.getSpecies().isEmpty()) {
            person.setSpecies(List.of(fetchNameFromUrlAsync(person.getSpecies().get(0)).block()));
        }
        if (person.getVehicles() != null) {
            person.setVehicles(fetchNamesFromUrlsAsync(person.getVehicles()));
        }
        if (person.getStarships() != null) {
            person.setStarships(fetchNamesFromUrlsAsync(person.getStarships()));
        }
        if (person.getHomeworld() != null) {
            person.setHomeworld(fetchNameFromUrlAsync(person.getHomeworld()).block());
        }
        return person;
    }

    /**
     * Obtiene una página de pilotos con los datos reemplazados por nombres.
     *
     * @param pageable Información de paginación.
     * @return Página de pilotos en formato DTO.
     */
    public Page<PilotDTO> getPilots(Pageable pageable) {
        Pageable fixedPageable = PageRequest.of(pageable.getPageNumber(), 10);
        int page = fixedPageable.getPageNumber() + 1;
        String nextUrl = urlBase + "?page=" + page;
        PeopleResponse allPeople = fetchAllPages(nextUrl);
        List<Person> people = replaceUrlsWithNames(allPeople.getResults());

        return new PageImpl<>(
                pilotMapper.toPilotsDTO(people),
                fixedPageable,
                allPeople.getCount());
    }

    /**
     * Consume una página específica de la API de pilotos.
     *
     * @param nextPageUrl URL de la página a consumir.
     * @return Respuesta con los datos de la página.
     */
    private PeopleResponse fetchAllPages(String nextPageUrl) {
        ResponseEntity<PeopleResponse> response = restTemplate.getForEntity(nextPageUrl, PeopleResponse.class);
        return response.getBody();
    }

    /**
     * Reemplaza las URLs en una lista de pilotos con los nombres correspondientes.
     *
     * @param people Lista de pilotos.
     * @return Lista de pilotos con datos actualizados.
     */
    private List<Person> replaceUrlsWithNames(List<Person> people) {
        return people.stream().map(person -> {
            if (person.getSpecies() != null && !person.getSpecies().isEmpty()) {
                person.setSpecies(List.of(fetchNameFromUrlAsync(person.getSpecies().get(0)).block()));
            }
            if (person.getVehicles() != null) {
                person.setVehicles(fetchNamesFromUrlsAsync(person.getVehicles()));
            }
            if (person.getStarships() != null) {
                person.setStarships(fetchNamesFromUrlsAsync(person.getStarships()));
            }
            if (person.getHomeworld() != null) {
                person.setHomeworld(fetchNameFromUrlAsync(person.getHomeworld()).block());
            }
            return person;
        }).collect(Collectors.toList());
    }

    /**
     * Consume una URL individual y obtiene el valor del atributo 'name'.
     *
     * @param url URL a consumir.
     * @return Mono con el nombre obtenido.
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
     * Consume una lista de URLs y obtiene los nombres correspondientes.
     *
     * @param urls Lista de URLs a consumir.
     * @return Lista de nombres obtenidos.
     */
    private List<String> fetchNamesFromUrlsAsync(List<String> urls) {
        return Flux.fromIterable(urls)
                .flatMap(this::fetchNameFromUrlAsync) // Ejecuta cada llamada en paralelo
                .collectList()
                .block(); // Bloquea para recoger los resultados
    }
}
