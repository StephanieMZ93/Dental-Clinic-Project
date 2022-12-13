package com.dh.StephanieMartinezEnd.service;

import com.dh.StephanieMartinezEnd.entity.Usuario;
import com.dh.StephanieMartinezEnd.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> buscandoUsuario=usuarioRepository.findByEmail(username);
        if (buscandoUsuario.isPresent()){
            return buscandoUsuario.get();
        }
        else{
            throw new UsernameNotFoundException("No existe el usuario con dicho correo en la bases de datos");
        }
    }
}

