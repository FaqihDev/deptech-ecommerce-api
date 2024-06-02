package com.jamsirat.atmapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Setter
@Getter
public class BadCredentialsException extends AuthenticationException {

    private Integer responseCode;
    private String exceptionMessage;

    public BadCredentialsException(String msg, Throwable cause) {
        super(msg, cause);
        this.responseCode = 401;
        this.exceptionMessage = msg;

    }

    public BadCredentialsException(String msg) {
        super(msg);
    }
}
