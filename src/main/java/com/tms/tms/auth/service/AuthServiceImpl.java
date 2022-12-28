package com.tms.tms.auth.service;

import com.tms.tms.config.security.JwtTokenProvider;
import com.tms.tms.auth.dto.LoginResponseDto;
import com.tms.tms.auth.dto.RegisterRequestDto;
import com.tms.tms.auth.dto.AuthResponseDto;
import com.tms.tms.auth.entity.User;
import com.tms.tms.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService{
    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {
        User user = new User(registerRequestDto.getUid(),
                encode(registerRequestDto.getPassword()),
                registerRequestDto.getName(),
                registerRequestDto.getEmail());

        if(registerRequestDto.getRole().equalsIgnoreCase("admin")){
            user.setRoles(Collections.singletonList("ROLE_ADMIN"));
        }else{
            user.setRoles(Collections.singletonList("ROLE_USER"));
        }
        try{
            User savedUser = userRepository.save(user);
            AuthResponseDto authResponseDto = AuthResponseDto.builder()
                    .id(savedUser.getId())
                    .uid(savedUser.getUid())
                    .name(savedUser.getName())
                    .email(savedUser.getEmail())
                    .roles(savedUser.getRoles())
                    .build();

            return authResponseDto;
        }catch(Exception e){
            // TODO: 예외 -> 400 bad request 되도록 핸들러 추가 필요
            throw new RuntimeException("회원가입에서 오류가 발생했습니다: "+e.getMessage());
        }
    }


    @Override
    public LoginResponseDto login(String uid, String password) throws RuntimeException {
        User user = userRepository.getByUid(uid);

        if(user == null){
            throw new RuntimeException("사용자가 존재하지 않습니다.");
        }

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("비밀 번호가 일치하지 않습니다.");
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(jwtTokenProvider.createToken(user.getUid(), user.getRoles()));
        return loginResponseDto;
    }

    public String encode(String password){
        return passwordEncoder.encode(password);
    }
}
