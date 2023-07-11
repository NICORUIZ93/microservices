package com.microservice.usuarioservice.repository;

import com.microservice.usuarioservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
