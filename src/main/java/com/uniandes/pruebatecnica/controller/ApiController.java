package com.uniandes.pruebatecnica.controller;

import com.uniandes.pruebatecnica.controller.dto.GeneralStarshipDTO;
import com.uniandes.pruebatecnica.controller.dto.PilotDTO;
import com.uniandes.pruebatecnica.controller.dto.SpecificStarshipDTO;
import com.uniandes.pruebatecnica.controller.dto.UpdateStarshipDTO;
import com.uniandes.pruebatecnica.service.GeneralStarshipsService;
import com.uniandes.pruebatecnica.service.PilotService;
import com.uniandes.pruebatecnica.service.SpecificStarshipsSevice;
import com.uniandes.pruebatecnica.service.UpdateStarshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador principal para gestionar los endpoints relacionados con naves espaciales y pilotos.
 */
@RestController
@RequestMapping("/Api")
@Tag(
        name = "Controlador",
        description = "Controlador que contiene todos los endpointes del proyecto."
)
public class ApiController {

    /**
     * Servicio para gestionar naves generales.
     */
    @Autowired
    private GeneralStarshipsService generalStarshipsService;

    /**
     * Servicio para gestionar pilotos.
     */
    @Autowired
    private PilotService pilotService;

    /**
     * Servicio para gestionar naves específicas.
     */
    @Autowired
    private SpecificStarshipsSevice specificStarshipsSevice;

    /**
     * Servicio para actualizar datos de naves.
     */
    @Autowired
    private UpdateStarshipService updateStarshipService;

    /**
     * Obtiene una lista paginada de naves generales.
     *
     * @param pageable objeto de paginación para limitar los resultados.
     * @return una lista de naves generales con estado HTTP 200.
     */
    @Operation(
            summary = "Obtiene una lista paginada de naves generales.",
            description = "Obtiene una lista paginada de naves generales.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Pageable.class)
                        )
                )
            }
    )
    @GetMapping("/NaveGeneral/")
    public ResponseEntity<?> getAllGeneralStarships(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return new ResponseEntity<>(generalStarshipsService.getGeneralStarships(pageable), HttpStatus.OK);
    }

    /**
     * Obtiene una nave general específica por su ID.
     *
     * @param id el identificador único de la nave.
     * @return los detalles de la nave específica con estado HTTP 200.
     */
    @Operation(
            summary = "Obtiene una nave general específica por su ID.",
            description = "Obtiene una nave general específica por su ID.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = GeneralStarshipDTO.class)
                        )
                )
            }
    )
    @GetMapping("/NaveGeneral/{id}")
    public ResponseEntity<?> getGeneralStarship(@PathVariable int id) {
        return new ResponseEntity<>(generalStarshipsService.getSpecificStarchip(id), HttpStatus.OK);
    }

    /**
     * Obtiene una lista paginada de pilotos.
     *
     * @param pageable objeto de paginación para limitar los resultados.
     * @return una lista de pilotos con estado HTTP 200.
     */
    @Operation(
            summary = "Obtiene una lista paginada de pilotos.",
            description = "Obtiene una lista paginada de pilotos.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Pageable.class)
                        )
                )
            }
    )
    @GetMapping("/Piloto/")
    public ResponseEntity<?> getAllPilots(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return new ResponseEntity<>(pilotService.getPilots(pageable), HttpStatus.OK);
    }

    /**
     * Obtiene un piloto específico por su ID.
     *
     * @param id el identificador único del piloto.
     * @return los detalles del piloto específico con estado HTTP 200.
     */
    @Operation(
            summary = "Obtiene un piloto específico por su ID.",
            description = "Obtiene un piloto específico por su ID.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PilotDTO.class)
                        )
                )
            }
    )
    @GetMapping("/Piloto/{id}")
    public ResponseEntity<?> getPilot(@PathVariable int id) {
        return new ResponseEntity<>(pilotService.getPilot(id), HttpStatus.OK);
    }

    /**
     * Obtiene una lista paginada de naves específicas.
     *
     * @param pageable objeto de paginación para limitar los resultados.
     * @return una lista de naves específicas con estado HTTP 200.
     */
    @Operation(
            summary = "Obtiene una lista paginada de naves específicas.",
            description = "Obtiene una lista paginada de naves específicas.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Pageable.class)
                        )
                )
            }
    )
    @GetMapping("/NaveEspecifico/")
    public ResponseEntity<?> getAllSpecificStarships(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return new ResponseEntity<>(specificStarshipsSevice.getSpecificStarships(pageable), HttpStatus.OK);
    }

    /**
     * Obtiene una nave específica por su ID.
     *
     * @param id el identificador único de la nave.
     * @return los detalles de la nave específica con estado HTTP 200.
     */
    @Operation(
            summary = "Obtiene una nave específica por su ID.",
            description = "Obtiene una nave específica por su ID.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = SpecificStarshipDTO.class)
                        )
                )
            }
    )
    @GetMapping("/NaveEspecifico/{id}")
    public ResponseEntity<?> getSpecificStarship(@PathVariable int id) {
        return new ResponseEntity<>(specificStarshipsSevice.getSpecificStarchip(id), HttpStatus.OK);
    }

    /**
     * Actualiza los datos de una nave por su ID.
     *
     * @param id el identificador único de la nave.
     * @param updateStarshipDTO objeto con los nuevos datos de la nave.
     * @return los detalles de la nave actualizada con estado HTTP 200.
     */
    @Operation(
            summary = "Actualiza los datos de una nave por su ID.",
            description = "Actualiza los datos de una nave por su ID. Esta actualización se hacer en una lista de naves que te tiene localmente y se almacena en memoria.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UpdateStarshipDTO.class)
                        )
                )
            }
    )
    @PutMapping("/ActualizarObjetoNave/{id}")
    public ResponseEntity<?> updateObjectStarship(@PathVariable String id, @Valid @RequestBody UpdateStarshipDTO updateStarshipDTO) {
        return new ResponseEntity<>(updateStarshipService.updateStarship(id, updateStarshipDTO), HttpStatus.OK);
    }

    /**
     * Obtiene los datos actualizados de una nave por su ID.
     *
     * @param id el identificador único de la nave.
     * @return los detalles actualizados de la nave con estado HTTP 200.
     */
    @Operation(
            summary = "Obtiene los datos actualizados de una nave por su ID.",
            description = "Obtiene los datos actualizados de una nave por su ID de una lista local.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UpdateStarshipDTO.class)
                        )
                )
            }
    )
    @GetMapping("/ActualizarObjetoNave/{id}")
    public ResponseEntity<?> getUpdatedStarship(@PathVariable String id) {
        return new ResponseEntity<>(updateStarshipService.getStarship(id), HttpStatus.OK);
    }
}
