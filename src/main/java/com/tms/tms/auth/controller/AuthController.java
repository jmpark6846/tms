package com.tms.tms.auth.controller;

import com.tms.tms.auth.dto.AuthResponseDto;
import com.tms.tms.auth.dto.LoginResponseDto;
import com.tms.tms.auth.dto.RegisterRequestDto;
import com.tms.tms.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

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

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e){
        HttpHeaders responseHeaders = new HttpHeaders();
        HashMap<String, String> map = new HashMap<>();

        map.put("error type", HttpStatus.BAD_REQUEST.getReasonPhrase());
        map.put("code", "400");
        map.put("message", e.getMessage());
        logger.error("Exception Handler: {}, {}",e.getMessage(), e.getCause());
        return new ResponseEntity<>(map, responseHeaders, HttpStatus.BAD_REQUEST);
    }
}
