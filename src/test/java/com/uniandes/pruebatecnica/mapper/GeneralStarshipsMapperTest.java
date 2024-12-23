package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.GeneralStarshipDTO;
import com.uniandes.pruebatecnica.model.StarShip;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;


public class GeneralStarshipsMapperTest {

    @Autowired
    private GeneralStarshipsMapper mapper = new GeneralStarshipsMapper();

    /**
     * Prueba para verificar que el método convierte correctamente un objeto
     * StarShip en un objeto GeneralStarshipDTO.
     */
    @Test
    void testToGeneralStarshipDTO_Success() {
        // Configuración del entorno de prueba
        StarShip starShip = StarShip.builder()
                .name("Millennium Falcon")
                .model("YT-1300")
                .cost_in_credits("100000")
                .max_atmosphering_speed("1050")
                .build();

        // Ejecución del método bajo prueba
        GeneralStarshipDTO result = mapper.toGeneralStarshipDTO(starShip);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals("Millennium Falcon", result.getNombreNave(), "El nombre de la nave no coincide");
        assertEquals("YT-1300", result.getModelo(), "El modelo de la nave no coincide");
        assertEquals("100000", result.getCosto(), "El costo de la nave no coincide");
        assertEquals("1050", result.getVelocidad(), "La velocidad de la nave no coincide");
    }

    /**
     * Prueba para verificar que el método maneja correctamente una lista de
     * objetos StarShip y la convierte en una lista de GeneralStarshipDTO.
     */
    @Test
    void testToGeneralStarshipsDTO_Success() {
        // Configuración del entorno de prueba
        StarShip starShip1 = StarShip.builder()
                .name("Millennium Falcon")
                .model("YT-1300")
                .cost_in_credits("100000")
                .max_atmosphering_speed("1050")
                .build();

        StarShip starShip2 = StarShip.builder()
                .name("X-Wing")
                .model("T-65")
                .cost_in_credits("149999")
                .max_atmosphering_speed("1050")
                .build();

        List<StarShip> starShips = List.of(starShip1, starShip2);

        // Ejecución del método bajo prueba
        List<GeneralStarshipDTO> result = mapper.toGeneralStarshipsDTO(starShips);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals(2, result.size(), "La lista resultante debe tener el mismo tamaño que la lista de entrada");

        // Verificar el primer elemento
        GeneralStarshipDTO dto1 = result.get(0);
        assertEquals("Millennium Falcon", dto1.getNombreNave(), "El nombre de la primera nave no coincide");
        assertEquals("YT-1300", dto1.getModelo(), "El modelo de la primera nave no coincide");

        // Verificar el segundo elemento
        GeneralStarshipDTO dto2 = result.get(1);
        assertEquals("X-Wing", dto2.getNombreNave(), "El nombre de la segunda nave no coincide");
        assertEquals("T-65", dto2.getModelo(), "El modelo de la segunda nave no coincide");
    }

    /**
     * Prueba para verificar que el método maneja correctamente una lista vacía.
     */
    @Test
    void testToGeneralStarshipsDTO_EmptyList() {
        // Configuración del entorno de prueba
        List<StarShip> starShips = List.of();

        // Ejecución del método bajo prueba
        List<GeneralStarshipDTO> result = mapper.toGeneralStarshipsDTO(starShips);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertTrue(result.isEmpty(), "El resultado debe ser una lista vacía si la entrada es una lista vacía");
    }

}
