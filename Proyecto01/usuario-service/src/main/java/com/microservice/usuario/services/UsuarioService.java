package com.microservice.usuario.services;

import com.microservice.usuario.entities.Usuario;

import java.util.List;


public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);

    Usuario getUsuario(Long usuarioId);

    List<Usuario> getAllUsuario();
}
