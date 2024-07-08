package com.jamsirat.atmapi.endpoint.handler;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdviceHandler {

    private final CustomExceptionHandler customExceptionHandler;


    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponse handleDataNotFoundException(DataNotFoundException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.NO_CONTENT,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(OutOfStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse outOfStockException(OutOfStockException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(UserProfileAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse UnauthorizedEmailUser(UserProfileAlreadyAddedException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }
    @ExceptionHandler(EmailNotVerifiedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse userProfileException(EmailNotVerifiedException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(EmailNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse userProfileException(EmailNotValidException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }
    @ExceptionHandler(UnauthorizedGrantingAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse unAuthorizeGrantRole(UnauthorizedGrantingAccessException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }
    @ExceptionHandler(EmailAlreadyVerifiedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse emailAlreadyVerified(EmailAlreadyVerifiedException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse userAlreadyExist(UserAlreadyExistException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(UserNotActivatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse userIsNotActivated(UserNotActivatedException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse badCredentials(BadCredentialsException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse userNotFound(UserNotFoundException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse invalidToken(InvalidTokenException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(RoleHasBeenAddedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse roleHasBeenAdded(RoleHasBeenAddedException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse invalidData(InvalidDataException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse constraintValidationException(ConstraintViolationException e) {
        String detailMessage  = extractValidationMessage(e.getConstraintViolations());
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST, detailMessage, "Make sure your data is valid / not blank");
    }

    private String extractValidationMessage(Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
    }


    @ExceptionHandler(IllegalHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse handleIllegalHeader(IllegalHeaderException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST, e.getExceptionMessage(), e.getDeveloperMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public HttpResponse handleAccessDenied(AccessDeniedException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.UNAUTHORIZED, e.getExceptionMessage(), e.getDeveloperMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public HttpResponse handleExpiredJwtException(ExpiredJwtException e) {
        String message = "JWT token has expired";
        String developerMessage = "Please login again.";
        return customExceptionHandler.createHttpResponse(HttpStatus.UNAUTHORIZED, message, developerMessage);
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public HttpResponse handleJwtException(JwtException e) {
        String message = "Invalid JWT token";
        String developerMessage = "The token provided is invalid.";
        return customExceptionHandler.createHttpResponse(HttpStatus.UNAUTHORIZED, message, developerMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponse handleRuntimeException(RuntimeException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponse handleRuntimeException(Exception e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponse handleRuntimeException(IOException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e.getMessage());
    }
}