package com.uniandes.pruebatecnica.controllers;
  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api")
public class ApiController {
    
    @GetMapping("/NaveGeneral")
    public ResponseEntity<?> getNaveGeneral(){
        
        return new ResponseEntity<>("h", HttpStatus.OK);
    }
    
    @GetMapping("/Piloto")
    public ResponseEntity<?> getPiloto(){
        
        return new ResponseEntity<>("h", HttpStatus.OK);
    }
    
    @GetMapping("/NaveEspecifico")
    public ResponseEntity<?> getNaveEspecifico(){
        
        return new ResponseEntity<>("h", HttpStatus.OK);
    }
    
    @GetMapping("/ActualizarObjetoNave")
    public ResponseEntity<?> getActualizarObjetoNave(){
        
        return new ResponseEntity<>("h", HttpStatus.OK);
    }
}
