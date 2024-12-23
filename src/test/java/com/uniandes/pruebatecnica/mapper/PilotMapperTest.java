package com.uniandes.pruebatecnica.mapper;

import com.uniandes.pruebatecnica.controller.dto.PilotDTO;
import com.uniandes.pruebatecnica.model.Person;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;


public class PilotMapperTest {
    
    @Autowired
    private PilotMapper mapper = new PilotMapper();
    
    /**
     * Prueba para verificar que el método convierte correctamente un objeto Person
     * en un objeto PilotDTO.
     */
    @Test
    void testToPilotDTO_Success() {
        // Configuración del entorno de prueba
        Person person = Person.builder()
                .name("Luke Skywalker")
                .height("172")
                .gender("male")
                .mass("77")
                .birth_year("19BBY")
                .homeworld("Tatooine")
                .vehicles(List.of("Speeder Bike"))
                .starships(List.of("X-Wing"))
                .species(List.of("Human"))
                .build();

        // Ejecución del método bajo prueba
        PilotDTO result = mapper.toPilotDTO(person);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals("Luke Skywalker", result.getNombre(), "El nombre no coincide");
        assertEquals("172", result.getAltura(), "La altura no coincide");
        assertEquals("male", result.getGenero(), "El género no coincide");
        assertEquals("77", result.getPeso(), "El peso no coincide");
        assertEquals("19BBY", result.getAñoNacimiento(), "El año de nacimiento no coincide");
        assertEquals("Tatooine", result.getNombrePlanetaOrigen(), "El planeta de origen no coincide");
        assertEquals(List.of("Speeder Bike"), result.getNombreVehiculos(), "Los vehículos no coinciden");
        assertEquals(List.of("X-Wing"), result.getNombreNaves(), "Las naves no coinciden");
        assertEquals("Human", result.getNombreEspecie(), "La especie no coincide");
    }

    /**
     * Prueba para verificar que el método maneja correctamente una especie ausente.
     */
    @Test
    void testToPilotDTO_NoSpecies() {
        // Configuración del entorno de prueba
        Person person = Person.builder()
                .name("Droid")
                .height("150")
                .gender("n/a")
                .mass("unknown")
                .species(List.of()) // Lista vacía de especies
                .build();

        // Ejecución del método bajo prueba
        PilotDTO result = mapper.toPilotDTO(person);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals("Sin especie", result.getNombreEspecie(), "El nombre de la especie debería ser 'Sin especie'");
    }

    /**
     * Prueba para verificar que el método convierte correctamente una lista de objetos Person
     * en una lista de objetos PilotDTO.
     */
    @Test
    void testToPilotsDTO_Success() {
        // Configuración del entorno de prueba
        Person person1 = Person.builder()
                .name("Luke Skywalker")
                .height("172")
                .gender("male")
                .species(List.of("Human"))
                .build();

        Person person2 = Person.builder()
                .name("Leia Organa")
                .height("150")
                .gender("female")
                .species(List.of("Human"))
                .build();

        List<Person> people = List.of(person1, person2);

        // Ejecución del método bajo prueba
        List<PilotDTO> result = mapper.toPilotsDTO(people);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals(2, result.size(), "La lista resultante debe tener el mismo tamaño que la lista de entrada");

        // Verificar el primer elemento
        PilotDTO dto1 = result.get(0);
        assertEquals("Luke Skywalker", dto1.getNombre(), "El nombre del primer piloto no coincide");
        assertEquals("Human", dto1.getNombreEspecie(), "La especie del primer piloto no coincide");

        // Verificar el segundo elemento
        PilotDTO dto2 = result.get(1);
        assertEquals("Leia Organa", dto2.getNombre(), "El nombre del segundo piloto no coincide");
        assertEquals("Human", dto2.getNombreEspecie(), "La especie del segundo piloto no coincide");
    }

    /**
     * Prueba para verificar que el método maneja correctamente una lista vacía.
     */
    @Test
    void testToPilotsDTO_EmptyList() {
        // Configuración del entorno de prueba
        List<Person> people = List.of();

        // Ejecución del método bajo prueba
        List<PilotDTO> result = mapper.toPilotsDTO(people);

        // Verificación de resultados esperados
        assertNotNull(result, "El resultado no debe ser nulo");
        assertTrue(result.isEmpty(), "El resultado debe ser una lista vacía si la entrada es una lista vacía");
    }
}
