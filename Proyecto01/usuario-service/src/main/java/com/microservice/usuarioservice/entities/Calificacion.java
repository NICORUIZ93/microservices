package com.microservice.usuarioservice.entities;

import lombok.Data;

@Data
public class Calificacion {

    private String calificacionId;
    private String usuarioId;
    private String hotelId;
    private int calificacion;
    private String observaciones;

    private Hotel hotel;
}
