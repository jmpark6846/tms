package com.tms.tms.config;

import com.tms.tms.auth.service.UserService;
import com.tms.tms.auth.service.UserServiceImpl;
import com.tms.tms.config.security.JwtTokenProvider;
import com.tms.tms.config.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(SecurityConfiguration.class)
public class TestConfiguration {
    @Bean public JwtTokenProvider jwtTokenProvider(){ return new JwtTokenProvider(new UserServiceImpl()); }

}
