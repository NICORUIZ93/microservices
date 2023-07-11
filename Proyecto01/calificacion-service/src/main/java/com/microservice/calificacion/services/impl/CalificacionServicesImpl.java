package com.microservice.calificacion.services.impl;

import com.microservice.calificacion.entities.Calificacion;
import com.microservice.calificacion.repositories.CalificacionRepository;
import com.microservice.calificacion.services.CalificacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionServicesImpl implements CalificacionService {

    private final CalificacionRepository calificacionRepository;

    public CalificacionServicesImpl(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    @Override
    public Calificacion create(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Override
    public List<Calificacion> getCalificaciones() {
        return calificacionRepository.findAll();
    }

    @Override
    public List<Calificacion> getCalificacionesByUserId(String usuarioId) {
        return calificacionRepository.findByUsuarioId(Long.valueOf(usuarioId));
    }

    @Override
    public List<Calificacion> getCalificacionesByHotelId(String hotelId) {
        return calificacionRepository.findByHotelId(Long.valueOf(hotelId));
    }
}
