package com.microservice.usuario.services;

import com.microservice.usuario.entities.Usuario;
import com.microservice.usuario.exceptions.ResourceNotFoundException;
import com.microservice.usuario.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicesImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServicesImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario getUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el Id: " + usuarioId));
    }

    @Override
    public List<Usuario> getAllUsuario() {
        return usuarioRepository.findAll();
    }

}
