package com.dh.StephanieMartinezEnd.service;

import com.dh.StephanieMartinezEnd.entity.Paciente;
import com.dh.StephanieMartinezEnd.exceptions.ResourceNotFoundException;
import com.dh.StephanieMartinezEnd.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteService {
    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {

        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente){
        LOGGER.info("Se comenzo el registro del paciente con nombre: " + paciente.getNombre());
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPacienteId(Long id){
        LOGGER.info("Se comenzo la búsqueda del paciente con ID: " + id);
        return pacienteRepository.findById(id);
    }



    public void actualizarPaciente(Paciente paciente){
        LOGGER.info("Se comenzo a actualizar al paciente con ID: " + paciente.getId());
        pacienteRepository.save(paciente);
    }

    public void eliminarPacienteId(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteEliminar = buscarPacienteId(id);
        if (pacienteEliminar.isPresent()) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Eliminado el paciente con ID: " + id);
        } else {
            throw new ResourceNotFoundException("No encontramos el odontologo con id= " + id +
                    "que se desea eliminar.");
        }

    }

    public List<Paciente> listarPacientes(){
        LOGGER.info("Se comenzo la búsqueda de los pacientes.");
        return pacienteRepository.findAll();
    }

    public Optional <Paciente> buscarPacientePorEmail(String email){
        LOGGER.info("Se comenzo la búsqueda del odontologo con Email: " + email);
        return pacienteRepository.findByEmail(email);
    }
}
