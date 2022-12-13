package com.dh.StephanieMartinezEnd.controller;


import com.dh.StephanieMartinezEnd.entity.Odontologo;
import com.dh.StephanieMartinezEnd.exceptions.ResourceNotFoundException;
import com.dh.StephanieMartinezEnd.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoId(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoId(id);
        if (odontologoBuscado.isPresent()) {
            //que el odontologo existe
            return ResponseEntity.ok(odontologoBuscado.get());
        } else{
            //no existe el odontologo dicho
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }


    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        //preguntar si existe el odontologo
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoId(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Se actualizó el odontologo con id= " + odontologo.getId());

        } else {
            return ResponseEntity.badRequest().body("No encontramos el paciente " +
                    "que se quiere modificar.");
        }
    }


    @DeleteMapping("/{id}")
        public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoId(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologoId(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el odontologo con id= "+id);
        }
        else {
            return ResponseEntity.badRequest().body("No encontramos el odontologo con id= " + id +
                    "que se desea eliminar.");
        }
    }


    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }
}