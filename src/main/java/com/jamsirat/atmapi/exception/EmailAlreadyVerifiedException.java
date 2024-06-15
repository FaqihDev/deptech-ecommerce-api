package com.jamsirat.atmapi.exception;

import lombok.*;
import org.springframework.security.core.AuthenticationException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailAlreadyVerifiedException extends RuntimeException implements CustomException {

    private String exceptionMessage;
    private String developerMessage;

}