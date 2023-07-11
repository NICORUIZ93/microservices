package com.microservice.usuarioservice.service.impl;

import com.microservice.usuarioservice.entities.Calificacion;
import com.microservice.usuarioservice.entities.Hotel;
import com.microservice.usuarioservice.entities.Usuario;
import com.microservice.usuarioservice.exceptions.ResourceNotFoundException;
import com.microservice.usuarioservice.external.services.HotelService;
import com.microservice.usuarioservice.repository.UsuarioRepository;
import com.microservice.usuarioservice.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final HotelService hotelService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RestTemplate restTemplate, HotelService hotelService) {
        this.usuarioRepository = usuarioRepository;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String randomUsuarioId = UUID.randomUUID().toString();
        usuario.setUsuarioId(randomUsuarioId);
        return usuarioRepository.save(usuario);

    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }


    @Override
    public Usuario getUsuario(String usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID : " + usuarioId));

        Calificacion[] calificacionesUsuario = restTemplate.getForObject("http://CALIFICACION-SERVICE/calificaciones/usuarios/" + usuario.getUsuarioId(), Calificacion[].class);
        List<Calificacion> calificaciones = Arrays.stream(calificacionesUsuario).collect(Collectors.toList());
        List<Calificacion> listaCalificacion = calificaciones.stream().map(calificacion -> {
            System.out.println(calificacion.getHotelId());
            Hotel hotel = hotelService.getHotel(calificacion.getHotelId());
            calificacion.setHotel(hotel);
            return calificacion;
        }).collect(Collectors.toList());

        usuario.setCalificaciones(listaCalificacion);
        return usuario;
    }
}
