package com.jamsirat.atmapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvalidTokenException extends RuntimeException {

    private Integer responseCode;
    private String exceptionMessage;

}
