package com.tms.tms.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    UserDetails getUserByUid(String uid) throws UsernameNotFoundException;
}
