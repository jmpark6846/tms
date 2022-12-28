package com.tms.tms.auth.controller;

import com.tms.tms.auth.dto.AuthResponseDto;
import com.tms.tms.auth.dto.LoginResponseDto;
import com.tms.tms.auth.dto.RegisterRequestDto;
import com.tms.tms.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/")
    public ResponseEntity<AuthResponseDto> register(@Validated @RequestBody RegisterRequestDto registerRequestDto){
        AuthResponseDto authResponseDto = authService.register(registerRequestDto);
        return ResponseEntity.ok(authResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<LoginResponseDto> login(@RequestParam String uid, @RequestParam String password){
        LoginResponseDto loginResponseDto = authService.login(uid, password);
        return ResponseEntity.ok(loginResponseDto);
    }
}
