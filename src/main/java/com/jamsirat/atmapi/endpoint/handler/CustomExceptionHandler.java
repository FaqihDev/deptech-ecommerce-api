package com.jamsirat.atmapi.endpoint.handler;

import com.jamsirat.atmapi.dto.base.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomExceptionHandler {

    public HttpResponse createHttpResponse(HttpStatus status, String message, String developerMessage) {
        return HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .status(status)
                .statusCode(status.value())
                .developerMessage(developerMessage)
                .message(message)
                .build();
    }
}