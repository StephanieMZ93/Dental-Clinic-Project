package com.dh.StephanieMartinezEnd.service;

import com.dh.StephanieMartinezEnd.entity.Odontologo;
import com.dh.StephanieMartinezEnd.exceptions.ResourceNotFoundException;
import com.dh.StephanieMartinezEnd.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OdontologoService {
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo){
        LOGGER.info("Se comenzo el registro del odontologo con nombre: " + odontologo.getNombre());
        return odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarOdontologoId(Long id){
        LOGGER.info("Se comenzo la búsqueda del odontologo con ID: " + id);
        return odontologoRepository.findById(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        LOGGER.info("Se comenzo a actualizar al odontologo con ID: " + odontologo.getId());
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologoId(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoEliminar = buscarOdontologoId(id);
        if (odontologoEliminar.isPresent()) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Eliminado el odontologo con ID: " + id);
        } else {
            throw new ResourceNotFoundException("No encontramos el odontologo con id= " + id +
                    "que se desea eliminar.");
        }

    }
        public List<Odontologo> listarOdontologos () {
        LOGGER.info("Se comenzo la búsqueda de los odontologos.");
        return odontologoRepository.findAll();
        }
}
