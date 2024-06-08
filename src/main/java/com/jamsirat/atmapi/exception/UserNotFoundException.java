package com.jamsirat.atmapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Getter
@Setter
public class UserNotFoundException extends AuthenticationException {

    private Integer responseCode;
    private String exceptionMessage;

    public UserNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
        this.responseCode = 401;
        this.exceptionMessage = msg;
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}