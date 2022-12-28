package com.tms.tms.auth.service;

import com.tms.tms.auth.dto.LoginResponseDto;
import com.tms.tms.auth.dto.RegisterRequestDto;
import com.tms.tms.auth.dto.AuthResponseDto;

public interface AuthService {
    AuthResponseDto register(RegisterRequestDto registerRequestDto);
    LoginResponseDto login(String uid, String password) throws RuntimeException;
}
