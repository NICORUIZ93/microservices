package com.microservice.hotel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "hoteles")
public class Hotel {
    @Id
    @GeneratedValue
    private Long id;


}
