package com.microservice.usuarioservice.repository;

import com.microservice.usuarioservice.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
