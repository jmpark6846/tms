package com.tms.tms.auth.service;

import com.tms.tms.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Override
    public UserDetails getUserByUid(String uid) throws UsernameNotFoundException {
        return userRepository.getByUid(uid);
    }
}
