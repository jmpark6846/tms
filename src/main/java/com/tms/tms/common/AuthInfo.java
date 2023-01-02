package com.tms.tms.common;

import org.springframework.security.core.Authentication;

public interface AuthInfo {

    Authentication getAuthentication();
}
