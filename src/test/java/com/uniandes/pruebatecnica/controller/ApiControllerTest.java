package com.uniandes.pruebatecnica.controller;

import com.uniandes.pruebatecnica.controller.dto.GeneralStarshipDTO;
import com.uniandes.pruebatecnica.controller.dto.PilotDTO;
import com.uniandes.pruebatecnica.controller.dto.SpecificStarshipDTO;
import com.uniandes.pruebatecnica.controller.dto.UpdateStarshipDTO;
import com.uniandes.pruebatecnica.service.GeneralStarshipsService;
import com.uniandes.pruebatecnica.service.PilotService;
import com.uniandes.pruebatecnica.service.SpecificStarshipsSevice;
import com.uniandes.pruebatecnica.service.UpdateStarshipService;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ApiControllerTest {
    
    @InjectMocks
    private ApiController apiController;

    @Mock
    private GeneralStarshipsService generalStarshipsService;

    @Mock
    private PilotService pilotService;

    @Mock
    private SpecificStarshipsSevice specificStarshipsSevice;

    @Mock
    private UpdateStarshipService updateStarshipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Prueba para obtener una lista paginada de naves generales.
     */
    @Test
    void testGetAllGeneralStarships() {
        Page<GeneralStarshipDTO> mockPage = new PageImpl<>(Collections.emptyList());
        when(generalStarshipsService.getGeneralStarships(any(Pageable.class))).thenReturn(mockPage);

        ResponseEntity<?> response = apiController.getAllGeneralStarships(Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPage, response.getBody());
    }

    /**
     * Prueba para obtener una nave general específica por ID.
     */
    @Test
    void testGetGeneralStarship() {
        GeneralStarshipDTO mockDto = new GeneralStarshipDTO();
        when(generalStarshipsService.getSpecificStarchip(1)).thenReturn(mockDto);

        ResponseEntity<?> response = apiController.getGeneralStarship(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    /**
     * Prueba para obtener una lista paginada de pilotos.
     */
    @Test
    void testGetAllPilots() {
        Page<PilotDTO> mockPage = new PageImpl<>(Collections.emptyList());
        when(pilotService.getPilots(any(Pageable.class))).thenReturn(mockPage);

        ResponseEntity<?> response = apiController.getAllPilots(Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPage, response.getBody());
    }

    /**
     * Prueba para obtener un piloto específico por ID.
     */
    @Test
    void testGetPilot() {
        PilotDTO mockDto = new PilotDTO();
        when(pilotService.getPilot(1)).thenReturn(mockDto);

        ResponseEntity<?> response = apiController.getPilot(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    /**
     * Prueba para obtener una lista paginada de naves específicas.
     */
    @Test
    void testGetAllSpecificStarships() {
        Page<SpecificStarshipDTO> mockPage = new PageImpl<>(Collections.emptyList());
        when(specificStarshipsSevice.getSpecificStarships(any(Pageable.class))).thenReturn(mockPage);

        ResponseEntity<?> response = apiController.getAllSpecificStarships(Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPage, response.getBody());
    }

    /**
     * Prueba para obtener una nave específica por ID.
     */
    @Test
    void testGetSpecificStarship() {
        SpecificStarshipDTO mockDto = new SpecificStarshipDTO();
        when(specificStarshipsSevice.getSpecificStarchip(1)).thenReturn(mockDto);

        ResponseEntity<?> response = apiController.getSpecificStarship(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    /**
     * Prueba para actualizar una nave por su ID.
     */
    @Test
    void testUpdateObjectStarship() {
        UpdateStarshipDTO mockDto = new UpdateStarshipDTO();
        when(updateStarshipService.updateStarship(eq("1"), any(UpdateStarshipDTO.class))).thenReturn(mockDto);

        UpdateStarshipDTO updateDto = new UpdateStarshipDTO();
        ResponseEntity<?> response = apiController.updateObjectStarship("1", updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }

    /**
     * Prueba para obtener los datos actualizados de una nave por su ID.
     */
    @Test
    void testGetUpdatedStarship() {
        UpdateStarshipDTO mockDto = new UpdateStarshipDTO();
        when(updateStarshipService.getStarship("1")).thenReturn(mockDto);

        ResponseEntity<?> response = apiController.getUpdatedStarship("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());
    }
}
