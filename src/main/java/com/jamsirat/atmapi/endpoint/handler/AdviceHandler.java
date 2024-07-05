package com.jamsirat.atmapi.endpoint.handler;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import com.jamsirat.atmapi.exception.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdviceHandler {

    private final CustomExceptionHandler customExceptionHandler;


    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponse handleDataNotFoundException(DataNotFoundException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.NOT_FOUND,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(OutOfStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse outOfStockException(OutOfStockException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST,e.getExceptionMessage(),e.getDeveloperMessage());
    }

    @ExceptionHandler(UserProfileAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse UnauhtorizedEmailUser(UserProfileAlreadyAddedException e) {
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

    @ExceptionHandler(TokenIsExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse handleInvalidToken(TokenIsExpiredException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST, e.getExceptionMessage(), e.getDeveloperMessage());
    }

    @ExceptionHandler(IllegalHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse handleIllegalHeader(IllegalHeaderException e) {
        return customExceptionHandler.createHttpResponse(HttpStatus.BAD_REQUEST, e.getExceptionMessage(), e.getDeveloperMessage());
    }
}