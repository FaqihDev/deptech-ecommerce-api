package com.jamsirat.atmapi.exception;

import lombok.*;
import org.springframework.security.core.AuthenticationException;


@Getter
@Setter
public class UserNotActivatedException extends AuthenticationException {

    private Integer responseCode;
    private String exceptionMessage;

    public UserNotActivatedException(String msg, Throwable cause) {
        super(msg, cause);
        this.responseCode = 401;
        this.exceptionMessage = msg;
    }

    public UserNotActivatedException(String msg) {
        super(msg);
    }
}
