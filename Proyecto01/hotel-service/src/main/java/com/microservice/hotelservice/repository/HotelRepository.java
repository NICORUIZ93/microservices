package com.microservice.hotelservice.repository;

import com.microservice.hotelservice.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
