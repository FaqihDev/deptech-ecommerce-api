package com.jamsirat.atmapi.exception;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HandlerJwtExpiredTokenException extends ExpiredJwtException {
    public HandlerJwtExpiredTokenException(String message) {
        super(null, null, message);
    }

    public HandlerJwtExpiredTokenException(Header header, Claims claims, String message, Throwable cause) {
        super(header, claims, message, cause);
    }
}
