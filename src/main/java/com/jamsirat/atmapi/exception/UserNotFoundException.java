package com.jamsirat.atmapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Getter
@Setter
public class UserNotFoundException extends AuthenticationException implements CustomException{

    private String exceptionMessage;
    private String developerMessage;

    public UserNotFoundException(String msg, Throwable cause,String developerMessage ) {
        super(msg, cause);
        this.exceptionMessage = msg;
        this.developerMessage = developerMessage;
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}