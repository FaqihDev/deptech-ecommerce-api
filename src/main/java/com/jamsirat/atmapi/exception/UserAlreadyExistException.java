package com.jamsirat.atmapi.exception;

import lombok.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class UserAlreadyExistException extends AuthenticationException {

    private Integer responseCode;
    private String exceptionMessage;

    public UserAlreadyExistException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserAlreadyExistException(String msg, String exceptionMessage) {
        super(msg);
        this.responseCode = 401;
        this.exceptionMessage = exceptionMessage;
    }


}
