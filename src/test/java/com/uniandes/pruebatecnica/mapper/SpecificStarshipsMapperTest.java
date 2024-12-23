package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.SpecificStarshipDTO;
import com.uniandes.pruebatecnica.model.StarShip;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;


public class SpecificStarshipsMapperTest {
    
    @Autowired
    private SpecificStarshipsMapper mapper = new SpecificStarshipsMapper();
    
    /**
     * Prueba para verificar que el método toSpecificStarshipDTO convierte correctamente
     * un objeto StarShip a un objeto SpecificStarshipDTO.
     */
    @Test
    void testToSpecificStarshipDTO_Success() {
        // Configuración del entorno de prueba
        StarShip starShip = StarShip.builder()
                .name("Millennium Falcon")
                .model("YT-1300 light freighter")
                .cost_in_credits("100000")
                .max_atmosphering_speed("1050")
                .crew("4")
                .passengers("6")
                .build();

        // Ejecución del método bajo prueba
        SpecificStarshipDTO result = mapper.toSpecificStarshipDTO(starShip);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals("Millennium Falcon", result.getNombreNave(), "El nombre de la nave no coincide");
        assertEquals("YT-1300 light freighter", result.getModelo(), "El modelo no coincide");
        assertEquals("100000", result.getCosto(), "El costo no coincide");
        assertEquals("1050", result.getVelocidad(), "La velocidad no coincide");
        assertEquals("4", result.getCapacidadCargaPersonal(), "La capacidad de carga personal no coincide");
        assertEquals("6", result.getCapacidadCargaPasajeros(), "La capacidad de carga de pasajeros no coincide");
    }

    /**
     * Prueba para verificar que el método toSpecificStarshipsDTO convierte correctamente
     * una lista de objetos StarShip en una lista de objetos SpecificStarshipDTO.
     */
    @Test
    void testToSpecificStarshipsDTO_Success() {
        // Configuración del entorno de prueba
        StarShip starShip1 = StarShip.builder()
                .name("Millennium Falcon")
                .model("YT-1300 light freighter")
                .cost_in_credits("100000")
                .max_atmosphering_speed("1050")
                .crew("4")
                .passengers("6")
                .build();

        StarShip starShip2 = StarShip.builder()
                .name("X-Wing")
                .model("T-65 X-Wing Starfighter")
                .cost_in_credits("150000")
                .max_atmosphering_speed("1200")
                .crew("1")
                .passengers("0")
                .build();

        List<StarShip> starships = List.of(starShip1, starShip2);

        // Ejecución del método bajo prueba
        List<SpecificStarshipDTO> result = mapper.toSpecificStarshipsDTO(starships);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals(2, result.size(), "La lista resultante debe tener el mismo tamaño que la lista de entrada");

        // Verificar el primer elemento
        SpecificStarshipDTO dto1 = result.get(0);
        assertEquals("Millennium Falcon", dto1.getNombreNave(), "El nombre de la primera nave no coincide");
        assertEquals("YT-1300 light freighter", dto1.getModelo(), "El modelo de la primera nave no coincide");

        // Verificar el segundo elemento
        SpecificStarshipDTO dto2 = result.get(1);
        assertEquals("X-Wing", dto2.getNombreNave(), "El nombre de la segunda nave no coincide");
        assertEquals("T-65 X-Wing Starfighter", dto2.getModelo(), "El modelo de la segunda nave no coincide");
    }

    /**
     * Prueba para verificar que el método toSpecificStarshipsDTO maneja correctamente
     * una lista vacía como entrada.
     */
    @Test
    void testToSpecificStarshipsDTO_EmptyList() {
        // Configuración del entorno de prueba
        List<StarShip> starships = List.of();

        // Ejecución del método bajo prueba
        List<SpecificStarshipDTO> result = mapper.toSpecificStarshipsDTO(starships);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertTrue(result.isEmpty(), "El resultado debe ser una lista vacía si la entrada es una lista vacía");
    }
}
