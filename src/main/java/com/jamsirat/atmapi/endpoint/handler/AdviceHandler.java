package com.jamsirat.atmapi.endpoint.handler;

import com.jamsirat.atmapi.dto.response.HttpResponse;
import com.jamsirat.atmapi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AdviceHandler {

        @ExceptionHandler(DataNotFoundException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public HttpResponse dataNotFoundException(DataNotFoundException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message(e.getExceptionMessage())
                    .build();
        }

        @ExceptionHandler(OutOfStockException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public HttpResponse outOfStockException(OutOfStockException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message(e.getExceptionMessage())
                    .build();
        }

        @ExceptionHandler(EmailAlreadyVerifiedException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public HttpResponse emailAlreadyVerifiedException(EmailAlreadyVerifiedException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(e.getExceptionMessage())
                    .developerMessage("Please login to your account")
                    .build();
        }

        @ExceptionHandler(InvalidDataException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public HttpResponse invaliDataException(InvalidDataException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("Data is invalid")
                    .build();
        }

        @ExceptionHandler(UserAlreadyExistException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public HttpResponse userAlreadyExistException(UserAlreadyExistException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.BAD_REQUEST)
                    .developerMessage("Please choose another email")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(e.getExceptionMessage())
                    .build();
        }

        @ExceptionHandler(UserNotActivatedException.class)
        @ResponseStatus(HttpStatus.FORBIDDEN)
        public HttpResponse userIsNotActivatedException(UserNotActivatedException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.FORBIDDEN)
                    .developerMessage("Please verify your account to the link we sent")
                    .statusCode(HttpStatus.FORBIDDEN.value())
                    .message(e.getExceptionMessage())
                    .build();
        }

        @ExceptionHandler(BadCredentialsException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public HttpResponse BadCredentialsException(BadCredentialsException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .message(e.getExceptionMessage())
                    .build();
        }

        @ExceptionHandler(UserNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public HttpResponse UnauhtorizedGrantRole(UserNotFoundException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .developerMessage("Please verify your account")
                    .message(e.getExceptionMessage())
                    .build();
        }

        @ExceptionHandler(InvalidTokenException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public HttpResponse UnauhtorizedGrantRole(InvalidTokenException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .developerMessage("")
                    .message(e.getExceptionMessage())
                    .build();
        }

    @ExceptionHandler(RoleHasBeenAddedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse UserHasBeenGranted(RoleHasBeenAddedException e) {
        return HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .developerMessage(e.getExceptionMessage())
                .message(e.getExceptionMessage())
                .build();
    }

    @ExceptionHandler(UnauthorizedGrantingAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public HttpResponse UnauhtorizedGrantRole(UnauthorizedGrantingAccessException e) {
        return HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .status(HttpStatus.UNAUTHORIZED)
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .developerMessage("You are not authorized to access this resource")
                .message(e.getExceptionMessage())
                .build();
    }

    @ExceptionHandler(EmailNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse UnauhtorizedGrantRole(EmailNotValidException e) {
        return HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .developerMessage("Please specify another email")
                .message(e.getExceptionMessage())
                .build();
    }
}