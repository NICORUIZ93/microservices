package com.microservice.usuarioservice.entities;

import lombok.Data;

@Data
public class Hotel {
    private String id;
    private String nombre;
    private String ubicacion;
    private String informacion;
}
