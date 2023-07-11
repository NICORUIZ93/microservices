package com.microservice.calificacion.services;

import com.microservice.calificacion.entities.Calificacion;

import java.util.List;

public interface CalificacionService {
    Calificacion create(Calificacion calificacion);

    List<Calificacion> getCalificaciones();

    List<Calificacion> getCalificacionesByUserId(Long usuarioId);

    List<Calificacion> getCalificacionesByHotelId(Long hotelId);
}
