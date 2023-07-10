package com.microservice.usuarioservice.service;

import com.microservice.usuarioservice.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);

    List<Usuario> getAllUsuarios();

    Usuario getUsuario(Long usuarioId);

}
