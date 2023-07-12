package com.microservice.authservice.controllers;

import com.microservice.authservice.DTO.AuthUserDto;
import com.microservice.authservice.DTO.TokenDto;
import com.microservice.authservice.entities.AuthUser;
import com.microservice.authservice.services.AuthUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserService authUserService;

    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto) {
        TokenDto tokenDto = authUserService.login(dto);
        if (tokenDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<TokenDto> validate(@RequestParam String token) {
        TokenDto tokenDto = authUserService.validate(token);
        if (tokenDto == null) {
            return null;
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody AuthUserDto dto) {
        AuthUser authUser = authUserService.save(dto);
        if (authUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authUser);
    }
}
