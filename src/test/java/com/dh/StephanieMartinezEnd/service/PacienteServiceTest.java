package com.dh.StephanieMartinezEnd.service;

import com.dh.StephanieMartinezEnd.entity.Domicilio;
import com.dh.StephanieMartinezEnd.entity.Paciente;
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
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void registrarPacienteTest(){
        Paciente guardarPaciente= new Paciente("Stephanie",
                "Martinez","123654", LocalDate.of(2022,11,28),
                "stefy@gmail.com",new Domicilio("Calle 9a",445,"Jamundi",
                "Valle"));
        Paciente pacienteGuardado=pacienteService.registrarPaciente(guardarPaciente);
        assertEquals(1L,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacienteIDTest(){
        Long buscarId=1L;
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacienteId(buscarId);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void listarPacientesTest(){
        List<Paciente> pacientes=pacienteService.listarPacientes();
        assertEquals(1,pacientes.size());
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Paciente actualizarPaciente= new Paciente(1L,"Martha",
                "Zuluaga","789654", LocalDate.of(2022,11,29),
                "rodo@gmail.com",new Domicilio(1L,"Carrera 10",454,"Bogota",
                "Cundinamarca"));
        pacienteService.actualizarPaciente(actualizarPaciente);
        Optional<Paciente> pacienteActualizado=pacienteService.buscarPacienteId(1L);
        assertEquals("Martha",pacienteActualizado.get().getNombre());
    }

    @Test
    @Order(5)
    public void eliminarPacienteTest() throws ResourceNotFoundException{
        Long eliminarId=1L;
        pacienteService.eliminarPacienteId(eliminarId);
        Optional<Paciente> pacienteEliminado=pacienteService.buscarPacienteId(eliminarId);
        assertFalse(pacienteEliminado.isPresent());
    }
}