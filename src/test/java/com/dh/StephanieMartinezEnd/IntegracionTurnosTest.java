package com.dh.StephanieMartinezEnd;

import com.dh.StephanieMartinezEnd.dto.TurnoDTO;
import com.dh.StephanieMartinezEnd.entity.Domicilio;
import com.dh.StephanieMartinezEnd.entity.Odontologo;
import com.dh.StephanieMartinezEnd.entity.Paciente;
import com.dh.StephanieMartinezEnd.service.OdontologoService;
import com.dh.StephanieMartinezEnd.service.PacienteService;
import com.dh.StephanieMartinezEnd.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    private void cargandoDatos(){
        Paciente agregarPaciente=pacienteService
                .registrarPaciente(new Paciente("Norberto","Martinez","79298563",
                        LocalDate.of(2022,12,7),"peto@gmail.com",
                        new Domicilio("Calle 12",10,"Cali","Valle")));
        Odontologo agregarOdontologo=odontologoService
                .registrarOdontologo(new Odontologo("MP56418","Lucia","Quintero"));
        TurnoDTO turnoDTO= new TurnoDTO();
        turnoDTO.setFecha(LocalDate.of(2022,12,8));
        turnoDTO.setOdontologoId(agregarOdontologo.getId());
        turnoDTO.setPacienteId(agregarPaciente.getId());
        turnoService.registrarTurno(turnoDTO);
    }
    @Test
    public void listadoTurnosTest() throws Exception {
        cargandoDatos();
        MvcResult respuesta=mockMvc.perform
                (MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
