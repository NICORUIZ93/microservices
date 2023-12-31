package com.microservice.authservice.services;

import com.microservice.authservice.DTO.AuthUserDto;
import com.microservice.authservice.DTO.NewUserDto;
import com.microservice.authservice.DTO.RequestDto;
import com.microservice.authservice.DTO.TokenDto;
import com.microservice.authservice.entities.AuthUser;
import com.microservice.authservice.repository.AuthUserRepository;
import com.microservice.authservice.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
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

    public AuthUser save(NewUserDto dto) {

        Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
        if (user.isPresent()) {
            return null;
        }
        String password = passwordEncoder.encode(dto.getPassword());
        AuthUser authUser = AuthUser
                .builder()
                .password(password)
                .userName(dto.getUserName())
                .role(dto.getRole())
                .build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto dto) throws UnsupportedEncodingException {
        Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
        System.out.println("login " + user);
        if (!user.isPresent()) {
            System.out.println("login  !isPresent");
            return null;
        }

        if (passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
            System.out.println("login matches");
            return new TokenDto(jwtProvider.createToken(user.get()));
        }
        return null;
    }

    public TokenDto validate(String token, RequestDto requestDto) {
        if (jwtProvider.validate(token, requestDto)) {
            return null;
        }
        String userName = jwtProvider.getUserNameFromToken(token);
        if (authUserRepository.findByUserName(userName).isEmpty()) {
            return null;
        }
        return new TokenDto(token);
    }
}
