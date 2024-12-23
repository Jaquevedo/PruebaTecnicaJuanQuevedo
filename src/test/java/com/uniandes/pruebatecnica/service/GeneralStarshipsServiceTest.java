package com.uniandes.pruebatecnica.service;

import com.uniandes.pruebatecnica.controller.dto.GeneralStarshipDTO;
import com.uniandes.pruebatecnica.mapper.GeneralStarshipsMapper;
import com.uniandes.pruebatecnica.model.StarShip;
import com.uniandes.pruebatecnica.model.response.StarshipsResponse;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GeneralStarshipsServiceTest {

    @InjectMocks
    private GeneralStarshipsService service;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private GeneralStarshipsMapper generalStarshipsMapper;

    private StarShip starShipMock;
    private StarshipsResponse starshipsResponseMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurar StarShip Mock
        starShipMock = StarShip.builder()
                .name("Millennium Falcon")
                .model("YT-1300 light freighter")
                .cost_in_credits("100000")
                .max_atmosphering_speed("1050")
                .build();

        // Configurar StarshipsResponse Mock
        starshipsResponseMock = StarshipsResponse.builder()
                .count(1)
                .results(List.of(starShipMock))
                .next("")
                .previous("")
                .build();
    }

    /**
     * Prueba para verificar que el método getSpecificStarchip devuelve
     * correctamente un DTO de nave espacial general basado en un ID.
     */
    @Test
    void testGetSpecificStarship_Success() {
        // Configuración de la simulación
        when(restTemplate.getForEntity(anyString(), eq(StarShip.class)))
                .thenReturn(ResponseEntity.ok(starShipMock));
        when(generalStarshipsMapper.toGeneralStarshipDTO(any(StarShip.class)))
                .thenReturn(GeneralStarshipDTO.builder()
                        .nombreNave(starShipMock.getName())
                        .modelo(starShipMock.getModel())
                        .costo(starShipMock.getCost_in_credits())
                        .velocidad(starShipMock.getMax_atmosphering_speed())
                        .build());

        // Ejecución del método bajo prueba
        GeneralStarshipDTO result = service.getSpecificStarchip(1);

        // Verificaciones
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals("Millennium Falcon", result.getNombreNave(), "El nombre de la nave no coincide");
        assertEquals("YT-1300 light freighter", result.getModelo(), "El modelo no coincide");
    }

    /**
     * Prueba para verificar que el método getGeneralStarships devuelve
     * correctamente una página de DTOs de naves espaciales generales.
     */
    @Test
    void testGetGeneralStarships_Success() {
        // Configuración de la simulación
        when(restTemplate.getForEntity(anyString(), eq(StarshipsResponse.class)))
                .thenReturn(ResponseEntity.ok(starshipsResponseMock));
        when(generalStarshipsMapper.toGeneralStarshipsDTO(anyList()))
                .thenReturn(List.of(GeneralStarshipDTO.builder()
                        .nombreNave(starShipMock.getName())
                        .modelo(starShipMock.getModel())
                        .costo(starShipMock.getCost_in_credits())
                        .velocidad(starShipMock.getMax_atmosphering_speed())
                        .build()));

        // Configuración de paginación
        Pageable pageable = PageRequest.of(0, 10);

        // Ejecución del método bajo prueba
        Page<GeneralStarshipDTO> result = service.getGeneralStarships(pageable);

        // Verificaciones
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals(1, result.getContent().size(), "El tamaño de la lista debe ser 1");
        assertEquals("Millennium Falcon", result.getContent().get(0).getNombreNave(), "El nombre de la nave no coincide");
    }

}
