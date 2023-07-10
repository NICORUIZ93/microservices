package com.microservice.usuarioservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuarioId;

    @Column(name = "nombre", length = 20)
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "informacion")
    private String informacion;

}
