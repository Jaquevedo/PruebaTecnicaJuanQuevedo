package com.uniandes.pruebatecnica.controller;

import com.uniandes.pruebatecnica.controller.dto.UpdateStarshipDTO;
import com.uniandes.pruebatecnica.service.GeneralStarshipsService;
import com.uniandes.pruebatecnica.service.PilotService;
import com.uniandes.pruebatecnica.service.SpecificStarshipsSevice;
import com.uniandes.pruebatecnica.service.UpdateStarshipService;
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

@RestController
@RequestMapping("/Api")
public class ApiController {

    @Autowired
    private GeneralStarshipsService generalStarshipsService;
    @Autowired
    private PilotService pilotService;
    @Autowired
    private SpecificStarshipsSevice specificStarshipsSevice;
    @Autowired
    private UpdateStarshipService updateStarshipService;
    
    //Endpoints primer ejercicio
    @GetMapping("/NaveGeneral/")
    public ResponseEntity<?> getAllGeneralStarships(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return new ResponseEntity<>(generalStarshipsService.getGeneralStarships(pageable), HttpStatus.OK);
    }

    @GetMapping("/NaveGeneral/{id}")
    public ResponseEntity<?> getGeneralStarship(@PathVariable int id) {
        return new ResponseEntity<>(generalStarshipsService.getSpecificStarchip(id), HttpStatus.OK);
    }

    //Endpoints segundo ejercicio
    @GetMapping("/Piloto/")
    public ResponseEntity<?> getAllPilots(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return new ResponseEntity<>(pilotService.getPilots(pageable), HttpStatus.OK);
    }

    @GetMapping("/Piloto/{id}")
    public ResponseEntity<?> getPilot(@PathVariable int id) {
        return new ResponseEntity<>(pilotService.getPilot(id), HttpStatus.OK);
    }

    //Endpoints tercer ejercicio
    @GetMapping("/NaveEspecifico/")
    public ResponseEntity<?> getAllSpecificStarships(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return new ResponseEntity<>(specificStarshipsSevice.getSpecificStarships(pageable), HttpStatus.OK);
    }

    @GetMapping("/NaveEspecifico/{id}")
    public ResponseEntity<?> getSpecificStarship(@PathVariable int id) {
        return new ResponseEntity<>(specificStarshipsSevice.getSpecificStarchip(id), HttpStatus.OK);
    }

    //Endpoints cuarto ejercicio
    @PutMapping("/ActualizarObjetoNave/{id}")
    public ResponseEntity<?> updateObjectStarship(@PathVariable String id,@Valid @RequestBody UpdateStarshipDTO updateStarshipDTO) {
        return new ResponseEntity<>(updateStarshipService.updateStarship(id, updateStarshipDTO), HttpStatus.OK);
    }
    
    @GetMapping("/ActualizarObjetoNave/{id}")
    public ResponseEntity<?> getUpdatedStarship(@PathVariable String id) {
        return new ResponseEntity<>(updateStarshipService.getStarship(id), HttpStatus.OK);
    }
}
