package com.microservice.usuario.controllers;

import com.microservice.usuario.entities.Usuario;
import com.microservice.usuario.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long usuarioId) {
        return new ResponseEntity<>(usuarioService.getUsuario(usuarioId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listUsuario() {
        return new ResponseEntity<>(usuarioService.getAllUsuario(), HttpStatus.OK);
    }
}
