package com.dh.StephanieMartinezEnd;

import com.dh.StephanieMartinezEnd.entity.Domicilio;
import com.dh.StephanieMartinezEnd.entity.Odontologo;
import com.dh.StephanieMartinezEnd.entity.Paciente;
import com.dh.StephanieMartinezEnd.service.OdontologoService;
import com.dh.StephanieMartinezEnd.service.PacienteService;
import com.dh.StephanieMartinezEnd.service.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class StephanieMartinezEndApplicationTests {
	@Test
	void contextLoads() {
	}

	@Autowired
	private  PacienteService pacienteService;
	@Autowired
	private OdontologoService odontologoService;
	@Autowired
	private TurnoService turnoService;

	Domicilio domicilio = new Domicilio("Calle 9a",445,"Jamundi",
			"Valle");
	Paciente paciente = new Paciente("Stephanie",
			"Martinez","123654", LocalDate.of(2022,11,28),
			"stefy@gmail.com", domicilio);
	Odontologo odontologo = new Odontologo("MDG234", "Maria","Guzman");

	@Test
	public void buscarPacienteId1(){
		pacienteService.registrarPaciente(paciente);
		Optional<Paciente> pacienteBuscado=pacienteService.buscarPacienteId(1l);
		Assertions.assertEquals("Stephanie",pacienteBuscado.get().getNombre());
	}

	@Test
	public void buscarOdontologoId1(){
		odontologoService.registrarOdontologo(odontologo);
		Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoId(1l);
		Assertions.assertEquals("Maria",odontologoBuscado.get().getNombre());
	}

}
