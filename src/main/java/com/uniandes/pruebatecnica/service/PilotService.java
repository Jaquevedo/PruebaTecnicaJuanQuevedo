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

@Service
public class PilotService {

    private final String urlBase = "https://swapi.py4e.com/api/people/";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PilotMapper pilotMapper;
    @Autowired
    private WebClient webClient;

    
    public PilotDTO getPilot(int id) {
        ResponseEntity<Person> response = restTemplate.getForEntity(urlBase + id, Person.class);
        return pilotMapper.toPilotDTO(replaceUrlWithNames(response.getBody()));
    }

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

    //Metodo para consumir la pagina actual de la Api
    private PeopleResponse fetchAllPages(String nextPageUrl) {
        ResponseEntity<PeopleResponse> response = restTemplate.getForEntity(nextPageUrl, PeopleResponse.class);
        return response.getBody();
    }

    //Método para reemplazar las URLs con los nombres correspondientes
    private List<Person> replaceUrlsWithNames(List<Person> people) {
        return people.stream().map(person -> {
            // Reemplazar nombreEspecie
            if (person.getSpecies() != null && !person.getSpecies().isEmpty()) {
                person.setSpecies(List.of(fetchNameFromUrlAsync(person.getSpecies().get(0)).block()));
            }

            // Reemplazar nombreVehiculos
            if (person.getVehicles() != null) {
                person.setVehicles(fetchNamesFromUrlsAsync(person.getVehicles()));
            }

            // Reemplazar nombreNaves
            if (person.getStarships() != null) {
                person.setStarships(fetchNamesFromUrlsAsync(person.getStarships()));
            }

            // Reemplazar nombrePlanetaOrigen
            if (person.getHomeworld() != null) {
                person.setHomeworld(fetchNameFromUrlAsync(person.getHomeworld()).block());
            }

            return person;
        }).collect(Collectors.toList());
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
                .flatMap(this::fetchNameFromUrlAsync) // Ejecuta cada llamada en paralelo
                .collectList()
                .block(); // Bloquear para recoger resultados
    }

}
