package com.microservice.authservice.services;

import com.microservice.authservice.DTO.AuthUserDto;
import com.microservice.authservice.DTO.TokenDto;
import com.microservice.authservice.entities.AuthUser;
import com.microservice.authservice.repository.AuthUserRepository;
import com.microservice.authservice.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthUserService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public AuthUser save(AuthUserDto authUserDto) {

        Optional<AuthUser> userService = authUserRepository.findByUserName(authUserDto.getUserName());
        if (userService.isPresent()) {
            return null;
        }
        String password = passwordEncoder.encode(authUserDto.getPassword());
        AuthUser authUser = AuthUser
                .builder()
                .password(password)
                .userName(authUserDto.getUserName())
                .build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
        if (!user.isPresent()) {
            return null;
        }

        if (passwordEncoder.matches(dto.getPassword(), dto.getUserName())) {
            return new TokenDto(jwtProvider.createToken(user.get()));
        }
        return null;
    }

    public TokenDto validate(String token) {
        if (!jwtProvider.validate(token)) {
            return null;
        }
        String userName = jwtProvider.getUserNameFromToken(token);

        if (!authUserRepository.findByUserName(userName).isPresent()) {
            return null;
        }
        return new TokenDto(token);
    }
}
