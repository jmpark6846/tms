package com.tms.tms.auth.service;

import com.tms.tms.auth.dto.AuthResponseDto;
import com.tms.tms.auth.dto.LoginResponseDto;
import com.tms.tms.auth.dto.RegisterRequestDto;
import com.tms.tms.auth.entity.User;
import com.tms.tms.auth.repository.UserRepository;
import com.tms.tms.config.security.JwtTokenProvider;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class AuthServiceTest {

    UserRepository userRepository = Mockito.mock(UserRepository.class);
    PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    JwtTokenProvider jwtTokenProvider = Mockito.mock(JwtTokenProvider.class);

    AuthServiceImpl authService;

    @BeforeEach
    void beforeEach(){
        this.authService = new AuthServiceImpl(userRepository, jwtTokenProvider, passwordEncoder);
    }

    @Test
    void register(){
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .uid("joon123")
                .password("passss")
                .email("joon@joon.co")
                .name("joon")
                .role("ROLE_USER")
                .build();


        Mockito.when(
                userRepository.save(any(User.class))
        ).then(returnsFirstArg());

        AuthResponseDto authResponseDto = authService.register(registerRequestDto);
        Assertions.assertEquals(authResponseDto.getUid(), registerRequestDto.getUid());
        Mockito.verify(userRepository).save(any());
    }

    @Test
    void login(){
        User user = User.builder()
                .uid("joon121")
                .password("some-random-chars")
                .email("joon@joon.co")
                .name("joon")
                .build();

        Mockito.when(passwordEncoder.matches("passs", user.getPassword()))
                        .thenReturn(true);
        Mockito.when(jwtTokenProvider.createToken(user.getUid(), user.getRoles()))
                .thenReturn("jwt-token");
        Mockito.when(userRepository.getByUid("joon121"))
                .thenReturn(user);

        LoginResponseDto loginResponseDto = authService.login("joon121", "passs");

        Assertions.assertNotNull(loginResponseDto.getToken());
    }
}
