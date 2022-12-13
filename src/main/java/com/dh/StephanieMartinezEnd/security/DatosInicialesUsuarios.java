package com.dh.StephanieMartinezEnd.security;

import com.dh.StephanieMartinezEnd.entity.Usuario;
import com.dh.StephanieMartinezEnd.entity.UsuarioRole;
import com.dh.StephanieMartinezEnd.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesUsuarios implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //crear un usuario como si fuera real
        //guardarlo en la base
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passSinCifrar="digital";
        String passwordCifrada1=cifrador.encode(passSinCifrar);
        Usuario agragarUsuario=new Usuario("Stephanie",
                "Stefy",
                "stefy93o@gmail.com",passwordCifrada1, UsuarioRole.ROLE_USER);
        usuarioRepository.save(agragarUsuario);

        //Crear un usuario tipo Admin
        String passwordCifrada2 = cifrador.encode("digitalHouse");
        agragarUsuario = new Usuario("Martha", "Malu", "malu2@gmail.com",
                passwordCifrada2, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(agragarUsuario);
    }
}

