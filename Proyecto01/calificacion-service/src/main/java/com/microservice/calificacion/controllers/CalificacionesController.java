package com.microservice.calificacion.controllers;

import com.microservice.calificacion.entities.Calificacion;
import com.microservice.calificacion.services.CalificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionesController {

    private final CalificacionService calificacionService;

    public CalificacionesController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @PostMapping
    public ResponseEntity<Calificacion> saveCalificacion(Calificacion calificacion) {
        return new ResponseEntity<>(calificacionService.create(calificacion), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Calificacion>> getAllCalificacion(Calificacion calificacion) {
        return new ResponseEntity<>(calificacionService.getCalificaciones(), HttpStatus.OK);
    }

    @GetMapping("usuarios/{usuarioId}")
    public ResponseEntity<List<Calificacion>> getAllCalificacionByUserId(@PathVariable Long usuarioId) {
        return new ResponseEntity<>(calificacionService.getCalificacionesByUserId(usuarioId), HttpStatus.OK);
    }

    @GetMapping("hoteles/{hotelId}")
    public ResponseEntity<List<Calificacion>> getAllCalificacionByHotelId(@PathVariable Long hotelId) {
        return new ResponseEntity<>(calificacionService.getCalificacionesByHotelId(hotelId), HttpStatus.OK);
    }
}
