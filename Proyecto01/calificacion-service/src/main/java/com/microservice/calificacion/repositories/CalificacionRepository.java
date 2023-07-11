package com.microservice.calificacion.repositories;

import com.microservice.calificacion.entities.Calificacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CalificacionRepository extends MongoRepository<Calificacion, Long> {
    List<Calificacion> findByUsuarioId(Long usuarioId);

    List<Calificacion> findByHotelId(Long hotelId);
}
