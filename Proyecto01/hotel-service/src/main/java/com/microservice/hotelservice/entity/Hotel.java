package com.microservice.hotelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoteles")
@Data
public class Hotel {

    @Id
    private String id;
    private String nombre;
    private String ubicacion;
    private String informacion;

}
