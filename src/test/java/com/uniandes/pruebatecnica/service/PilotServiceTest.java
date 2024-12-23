package com.uniandes.pruebatecnica.service;

import com.uniandes.pruebatecnica.controller.dto.PilotDTO;
import com.uniandes.pruebatecnica.mapper.PilotMapper;
import com.uniandes.pruebatecnica.model.Person;
import com.uniandes.pruebatecnica.model.response.ApiNameResponse;
import com.uniandes.pruebatecnica.model.response.PeopleResponse;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class PilotServiceTest {

    @InjectMocks
    private PilotService service;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PilotMapper pilotMapper;

    @Mock
    private WebClient webClient;

    private Person mockPerson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockPerson = Person.builder().
                name("Luke Skywalker").
                height("172").
                mass("77").
                homeworld("https://swapi.py4e.com/api/planets/1/").
                species(List.of("https://swapi.py4e.com/api/planets/1/")).
                starships(List.of("https://swapi.py4e.com/api/planets/1/")).
                vehicles(List.of("https://swapi.py4e.com/api/planets/1/")).
                build();
    }

    /**
     * Prueba unitaria para el método `getPilot`. Verifica que se obtienen los
     * datos de un piloto específico y se realiza el mapeo correctamente.
     */
    @Test
    void testGetPilot_Success() {
        // Datos simulados
        PilotDTO mockPilotDTO = PilotDTO.builder()
                .nombre("Luke Skywalker")
                .altura("172")
                .peso("77")
                .nombrePlanetaOrigen("Tatooine")
                .nombreEspecie("Human")
                .build();

        when(restTemplate.getForEntity(anyString(), eq(Person.class)))
                .thenReturn(ResponseEntity.ok(mockPerson));
        when(pilotMapper.toPilotDTO(mockPerson)).thenReturn(mockPilotDTO);

        WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpecMock = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpecMock = mock(WebClient.ResponseSpec.class);

        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(eq(ApiNameResponse.class)))
                .thenReturn(Mono.just(new ApiNameResponse("")));

        // Ejecución
        PilotDTO result = service.getPilot(1);

        // Verificación
        assertEquals("Luke Skywalker", result.getNombre(), "El nombre del piloto no coincide");
        assertEquals("Tatooine", result.getNombrePlanetaOrigen(), "El nombre del planeta de origen no coincide");
        assertEquals("Human", result.getNombreEspecie(), "El nombre de la specia no coincide");
    }

    /**
     * Prueba unitaria para el método `getPilots`. Verifica que se obtiene una
     * lista de pilotos correctamente mapeada.
     */
    @Test
    void testGetPilots_Success() {
        // Datos simulados        
        PeopleResponse mockResponse = PeopleResponse.builder()
                .count(1)
                .results(List.of(mockPerson))
                .build();

        PilotDTO mockPilotDTO = PilotDTO.builder()
                .nombre("Luke Skywalker")
                .altura("172")
                .peso("77")
                .nombrePlanetaOrigen("Tatooine")
                .nombreEspecie("Human")
                .build();

        when(restTemplate.getForEntity(anyString(), eq(PeopleResponse.class)))
                .thenReturn(ResponseEntity.ok(mockResponse));
        when(pilotMapper.toPilotsDTO(mockResponse.getResults())).thenReturn(List.of(mockPilotDTO));

        WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpecMock = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpecMock = mock(WebClient.ResponseSpec.class);

        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(eq(ApiNameResponse.class)))
                .thenReturn(Mono.just(new ApiNameResponse("")));

        // Ejecución
        List<PilotDTO> result = service.getPilots(PageRequest.of(0, 10)).getContent();

        // Verificación
        assertEquals(1, result.size(), "El tamaño de la lista de pilotos no coincide");
        assertEquals("Luke Skywalker", result.get(0).getNombre(), "El nombre del piloto no coincide");
    }

}
