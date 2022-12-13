package com.dh.StephanieMartinezEnd.service;

import com.dh.StephanieMartinezEnd.entity.Domicilio;
import com.dh.StephanieMartinezEnd.entity.Odontologo;
import com.dh.StephanieMartinezEnd.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void registrarOdontologoTest(){
        Odontologo guardarOdontologo= new Odontologo("MDG234", "Maria","Guzman");
        Odontologo odontologoGuardado=odontologoService.registrarOdontologo(guardarOdontologo);
        assertEquals(1L,odontologoGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologoIDTest(){
        Long buscarId=1L;
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoId(buscarId);
        assertNotNull(odontologoBuscado.get());
    }
    @Test
    @Order(3)
    public void listarOdontologosTest(){
        List<Odontologo> odontologos=odontologoService.listarOdontologos();
        assertEquals(1,odontologos.size());
    }
    @Test
    @Order(4)
    public void actualizarOdontologoTest(){
        Odontologo actualizarOdontologo= new Odontologo("NMK908", "Oscar", "Vasquez");
        odontologoService.actualizarOdontologo(actualizarOdontologo);
        Optional<Odontologo> odontologoActualizado=odontologoService.buscarOdontologoId(1L);
        assertEquals("Martha",odontologoActualizado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        Long eliminarId=1L;
        odontologoService.eliminarOdontologoId(eliminarId);
        Optional<Odontologo> odontologoEliminado=odontologoService.buscarOdontologoId(eliminarId);
        assertFalse(odontologoEliminado.isPresent());
    }
}
