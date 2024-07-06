package com.jamsirat.atmapi.dto.base;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
public  class HttpResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -8781718059336021092L;

    private String timeStamp;
    private String developerMessage;
    private String message;
    private HttpStatus status;
    private int statusCode;
    private T data;


    public static <T> HttpResponse<T> buildHttpResponse(String developerMessage, String message, HttpStatus status, int statusCode, T data) {
        return HttpResponse.<T>builder()
                .timeStamp(LocalDateTime.now().toString())
                .developerMessage(developerMessage)
                .message(message)
                .status(status)
                .statusCode(statusCode)
                .data(data)
                .build();
    }


}