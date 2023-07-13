package com.microservice.authservice.controllers;

import com.microservice.authservice.DTO.AuthUserDto;
import com.microservice.authservice.DTO.NewUserDto;
import com.microservice.authservice.DTO.RequestDto;
import com.microservice.authservice.DTO.TokenDto;
import com.microservice.authservice.entities.AuthUser;
import com.microservice.authservice.services.AuthUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserService authUserService;

    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto) throws UnsupportedEncodingException {
        System.out.println(dto);
        TokenDto tokenDto = authUserService.login(dto);
        System.out.println("Token: " + tokenDto);
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto requestDto) {
        TokenDto tokenDto = authUserService.validate(token, requestDto);
        if (tokenDto == null) {
            return null;
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserDto dto) {
        AuthUser authUser = authUserService.save(dto);
        if (authUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authUser);
    }
}
