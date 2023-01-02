package com.tms.tms.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class Forbidden extends AccessDeniedException{
    public static String msg ="권한이 없습니다.";
    public Forbidden() {
        super(msg);
    }

    public Forbidden(String msg) {
        super(msg);
    }
}
