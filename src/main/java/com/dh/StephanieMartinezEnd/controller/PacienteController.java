package com.dh.StephanieMartinezEnd.controller;


import com.dh.StephanieMartinezEnd.entity.Paciente;
import com.dh.StephanieMartinezEnd.exceptions.ResourceNotFoundException;
import com.dh.StephanieMartinezEnd.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacienteId(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacienteId(id);
        if (pacienteBuscado.isPresent()) {
            //que los pacientes existe
            return ResponseEntity.ok(pacienteBuscado.get());
        } else{
            //no existe el paciente dicho
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/email/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@PathVariable String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorEmail(email);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        //chequeo para controlar que sea un correo sin usar
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacientePorEmail(paciente.getEmail());
        if (pacienteBuscado.isPresent()){
            //existe un paciente con ese email
            return ResponseEntity.badRequest().build();
        }
        else{
            return ResponseEntity.ok(pacienteService.registrarPaciente(paciente));
        }
    }


    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        //preguntar si existe el paciente
        Optional <Paciente> pacienteBuscado = pacienteService.buscarPacienteId(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizó al paciente con id= " + paciente.getId());

        } else {
            return ResponseEntity.badRequest().body("No se pudo actualizar al paciente con id= " + paciente.getId() +
                    " porque no existe en la base de datos.");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPacienteId(@PathVariable Long id) throws ResourceNotFoundException {
        Optional <Paciente> pacienteBuscado=pacienteService.buscarPacienteId(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPacienteId(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el paciente con id= "+id);
        }
        else {
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No existe" +
                    " el ID= "+id+" asociado a un paciente en la base de datos.");
        }
    }


    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }
}

