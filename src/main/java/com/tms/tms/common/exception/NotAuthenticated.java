package com.tms.tms.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)

public class NotAuthenticated extends AccessDeniedException {
    public static String msg ="로그인이 필요합니다.";

    public NotAuthenticated() {
        super(msg);
    }

    public NotAuthenticated(String msg) {
        super(msg);
    }
}
