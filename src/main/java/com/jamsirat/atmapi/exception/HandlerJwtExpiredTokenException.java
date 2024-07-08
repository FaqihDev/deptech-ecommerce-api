package com.jamsirat.atmapi.exception;



import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public abstract class HandlerJwtExpiredTokenException extends RuntimeException implements CustomException {
    private String message;
    private String developerMessage;


}
