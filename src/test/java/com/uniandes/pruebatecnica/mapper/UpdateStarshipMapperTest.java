package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.UpdateStarshipDTO;
import com.uniandes.pruebatecnica.model.StarShip;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateStarshipMapperTest {

    @Autowired
    private UpdateStarshipMapper updateStarshipMapper = new UpdateStarshipMapper();

    /**
     * Prueba para verificar que el método convierte correctamente un objeto
     * StarShip en un objeto UpdateStarshipDTO con todos los campos mapeados
     * correctamente.
     */
    @Test
    void testToUpdateStarshipDTO_Success() {
        // Configuración del entorno de prueba
        StarShip starShip = StarShip.builder()
                .name("Millennium Falcon")
                .model("YT-1300")
                .cost_in_credits("100000")
                .max_atmosphering_speed("1050")
                .crew("4")
                .passengers("6")
                .pilots(List.of("Han Solo", "Chewbacca"))
                .url("https://swapi.py4e.com/api/starships/10/")
                .build();

        // Ejecución del método bajo prueba
        UpdateStarshipDTO result = updateStarshipMapper.toUpdateStarshipDTO(starShip);

        // Verificación de resultados esperados
        assertEquals("Millennium Falcon", result.getNombreNave(), "El nombre de la nave no coincide");
        assertEquals("YT-1300", result.getModelo(), "El modelo de la nave no coincide");
        assertEquals("100000", result.getCosto(), "El costo de la nave no coincide");
        assertEquals("1050", result.getVelocidad(), "La velocidad de la nave no coincide");
        assertEquals("4", result.getCapacidadCargaPersonal(), "La capacidad de personal no coincide");
        assertEquals("6", result.getCapacidadCargaPasajeros(), "La capacidad de pasajeros no coincide");
        assertEquals(List.of("Han Solo", "Chewbacca"), result.getNombrePilotos(), "Los nombres de los pilotos no coinciden");
        assertEquals("10", result.getId(), "El ID extraído de la URL no coincide");
    }

    /**
     * Prueba para verificar que si alguno de los campos opcionales de StarShip
     * es nulo, el DTO resultante maneja correctamente estos valores nulos.
     */
    @Test
    void testToUpdateStarshipDTO_NullOptionalFields() {
        // Configuración del entorno de prueba
        StarShip starShip = StarShip.builder()
                .name("Slave I")
                .model("Firespray-31")
                .cost_in_credits(null) // Campo opcional nulo
                .max_atmosphering_speed("1000")
                .crew("1")
                .passengers(null) // Campo opcional nulo
                .pilots(null) // Campo opcional nulo
                .url("https://swapi.py4e.com/api/starships/21/")
                .build();

        // Ejecución del método bajo prueba
        UpdateStarshipDTO result = updateStarshipMapper.toUpdateStarshipDTO(starShip);

        // Verificación de resultados esperados
        assertEquals("Slave I", result.getNombreNave(), "El nombre de la nave no coincide");
        assertEquals("Firespray-31", result.getModelo(), "El modelo de la nave no coincide");
        assertNull(result.getCosto(), "El costo debe ser nulo si el valor original es nulo");
        assertEquals("1000", result.getVelocidad(), "La velocidad de la nave no coincide");
        assertEquals("1", result.getCapacidadCargaPersonal(), "La capacidad de personal no coincide");
        assertNull(result.getCapacidadCargaPasajeros(), "La capacidad de pasajeros debe ser nula");
        assertNull(result.getNombrePilotos(), "Los pilotos deben ser nulos si el valor original es nulo");
        assertEquals("21", result.getId(), "El ID extraído de la URL no coincide");
    }

    /**
     * Prueba para verificar que el método convierte correctamente una lista de
     * StarShip en una lista de UpdateStarshipDTO.
     */
    @Test
    void testToSpecificStarshipsDTO_Success() {
        // Configuración del entorno de prueba
        StarShip starShip1 = StarShip.builder()
                .name("Millennium Falcon")
                .model("YT-1300")
                .cost_in_credits("100000")
                .max_atmosphering_speed("1050")
                .crew("4")
                .passengers("6")
                .pilots(List.of("Han Solo", "Chewbacca"))
                .url("https://swapi.py4e.com/api/starships/10/")
                .build();

        StarShip starShip2 = StarShip.builder()
                .name("X-Wing")
                .model("T-65")
                .cost_in_credits("149999")
                .max_atmosphering_speed("1050")
                .crew("1")
                .passengers("0")
                .pilots(List.of("Luke Skywalker"))
                .url("https://swapi.py4e.com/api/starships/20/")
                .build();

        List<StarShip> starShips = List.of(starShip1, starShip2);

        // Ejecución del método bajo prueba
        List<UpdateStarshipDTO> result = updateStarshipMapper.toSpecificStarshipsDTO(starShips);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals(2, result.size(), "La lista resultante debe contener el mismo número de elementos que la lista de entrada");

        // Verificar el primer elemento
        UpdateStarshipDTO dto1 = result.get(0);
        assertEquals("Millennium Falcon", dto1.getNombreNave(), "El nombre de la primera nave no coincide");
        assertEquals("YT-1300", dto1.getModelo(), "El modelo de la primera nave no coincide");
        assertEquals("10", dto1.getId(), "El ID extraído de la URL de la primera nave no coincide");

        // Verificar el segundo elemento
        UpdateStarshipDTO dto2 = result.get(1);
        assertEquals("X-Wing", dto2.getNombreNave(), "El nombre de la segunda nave no coincide");
        assertEquals("T-65", dto2.getModelo(), "El modelo de la segunda nave no coincide");
        assertEquals("20", dto2.getId(), "El ID extraído de la URL de la segunda nave no coincide");
    }

    /**
     * Prueba para verificar que el método maneja correctamente una lista vacía.
     */
    @Test
    void testToSpecificStarshipsDTO_EmptyList() {
        // Configuración del entorno de prueba
        List<StarShip> starShips = List.of();

        // Ejecución del método bajo prueba
        List<UpdateStarshipDTO> result = updateStarshipMapper.toSpecificStarshipsDTO(starShips);

        // Verificación de resultados esperados
        assertTrue(result.isEmpty(), "El resultado debe ser una lista vacía si la entrada es una lista vacía");
    }
}
