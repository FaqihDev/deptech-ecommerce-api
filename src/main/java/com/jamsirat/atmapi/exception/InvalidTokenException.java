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
public class InvalidTokenException extends RuntimeException implements CustomException {

    private String exceptionMessage;
    private String developerMessage;

}