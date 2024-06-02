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
                    .message("Data not found")
                    .build();
        }

        @ExceptionHandler(OutOfStockException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public HttpResponse outOfStockException(OutOfStockException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Sorry, we are out of stock")
                    .build();
        }

        @ExceptionHandler(EmailAlreadyVerifiedException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public HttpResponse emailAlreadyVerifiedException(EmailAlreadyVerifiedException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("Email is already verified")
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
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("User is already exist")
                    .build();
        }

        @ExceptionHandler(UserNotActivatedException.class)
        @ResponseStatus(HttpStatus.FORBIDDEN)
        public HttpResponse userIsNotActivatedException(UserNotActivatedException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.FORBIDDEN)
                    .statusCode(HttpStatus.FORBIDDEN.value())
                    .message("User is not activated")
                    .build();
        }

        @ExceptionHandler(BadCredentialsException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public HttpResponse BadCredentialsException(BadCredentialsException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .message("Invalid username or password")
                    .build();
        }

        @ExceptionHandler(UserNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public HttpResponse UserNotFoundException(UserNotFoundException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .developerMessage("Please verify your account")
                    .message("User is not found")
                    .build();
        }
    }