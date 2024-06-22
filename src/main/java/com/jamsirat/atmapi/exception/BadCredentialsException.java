package com.jamsirat.atmapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Setter
@Getter
public class BadCredentialsException extends AuthenticationException implements CustomException {

    private String exceptionMessage;
    private String developerMessage;

    public BadCredentialsException(String msg, Throwable cause, String developerMessage) {
        super(msg, cause);
        this.exceptionMessage = msg;
        this.developerMessage = developerMessage;

    }

    public BadCredentialsException(String msg) {
        super(msg);
    }
}