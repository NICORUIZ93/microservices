package com.microservice.usuarioservice.external.services;

import com.microservice.usuarioservice.entities.Calificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "CALIFICACION-SERVICE")
public interface CalificacionService {

    @PostMapping()
    ResponseEntity<Calificacion> guardarCalificacion(Calificacion calificacion);

    @PostMapping("/calificaciones/{calificacionId}")
    ResponseEntity<Calificacion> actualizarCalificacion(@PathVariable String calificacionId);

    @DeleteMapping("/calificaciones/{calificacionId}")
    void eliminarCalificacion(@PathVariable String calificacionId);
}
