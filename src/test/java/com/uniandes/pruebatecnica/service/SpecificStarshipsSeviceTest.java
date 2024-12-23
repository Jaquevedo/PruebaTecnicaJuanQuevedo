package com.uniandes.pruebatecnica.service;

import com.uniandes.pruebatecnica.controller.dto.SpecificStarshipDTO;
import com.uniandes.pruebatecnica.mapper.SpecificStarshipsMapper;
import com.uniandes.pruebatecnica.model.StarShip;
import com.uniandes.pruebatecnica.model.response.StarshipsResponse;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpecificStarshipsSeviceTest {
    
    @InjectMocks
    private SpecificStarshipsSevice specificStarshipsSevice;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SpecificStarshipsMapper specificStarshipsMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Prueba para obtener una nave específica por su ID.
     */
    @Test
    void testGetSpecificStarchip() {
        StarShip mockStarShip = new StarShip();
        SpecificStarshipDTO mockDto = new SpecificStarshipDTO();

        // Simula las respuestas del RestTemplate y el Mapper
        when(restTemplate.getForEntity(anyString(), eq(StarShip.class)))
                .thenReturn(ResponseEntity.ok(mockStarShip));
        when(specificStarshipsMapper.toSpecificStarshipDTO(mockStarShip)).thenReturn(mockDto);

        SpecificStarshipDTO result = specificStarshipsSevice.getSpecificStarchip(1);

        assertEquals(mockDto, result);
    }

    /**
     * Prueba para obtener una página de naves específicas.
     */
    @Test
    void testGetSpecificStarships() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        StarshipsResponse mockResponse = new StarshipsResponse();
        mockResponse.setCount(1);
        mockResponse.setResults(List.of(new StarShip()));
        SpecificStarshipDTO mockDto = new SpecificStarshipDTO();
        Page<SpecificStarshipDTO> expectedPage = new PageImpl<>(List.of(mockDto), pageable, 1);

        // Simula las respuestas del RestTemplate y el Mapper
        when(restTemplate.getForEntity(anyString(), eq(StarshipsResponse.class)))
                .thenReturn(ResponseEntity.ok(mockResponse));
        when(specificStarshipsMapper.toSpecificStarshipsDTO(mockResponse.getResults()))
                .thenReturn(List.of(mockDto));

        Page<SpecificStarshipDTO> result = specificStarshipsSevice.getSpecificStarships(pageable);

        assertEquals(expectedPage, result);

    }
}
