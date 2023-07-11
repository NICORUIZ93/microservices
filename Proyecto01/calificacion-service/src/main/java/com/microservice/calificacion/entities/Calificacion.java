package com.microservice.calificacion.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@Document(collection = "calificaciones")
public class Calificacion {
    @MongoId
    private String id;
    private Long usuarioId;
    private Long hotelId;
    private int calificacion;
    private String observaciones;
}
