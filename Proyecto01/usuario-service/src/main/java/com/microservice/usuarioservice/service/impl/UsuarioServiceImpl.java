package com.microservice.usuarioservice.service.impl;

import com.microservice.usuarioservice.entities.Usuario;
import com.microservice.usuarioservice.exceptions.ResourceNotFoundException;
import com.microservice.usuarioservice.repository.UsuarioRepository;
import com.microservice.usuarioservice.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario saveUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }


    @Override
    public Usuario getUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID : " + usuarioId));
    }
}
