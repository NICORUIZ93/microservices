package com.microservice.hotelservice.service.impl;

import com.microservice.hotelservice.entity.Hotel;
import com.microservice.hotelservice.exceptions.ResourceNotFoundException;
import com.microservice.hotelservice.repository.HotelRepository;
import com.microservice.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }


    @Override
    public Hotel get(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel no encontrado con el ID : " + id));
    }
}
