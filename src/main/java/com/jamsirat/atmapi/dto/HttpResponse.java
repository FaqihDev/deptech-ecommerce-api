package com.jamsirat.atmapi.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
public class HttpResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5664667235415243213L;

    private String timeStamp;
    private String developerMessage;
    private String message;
    private HttpStatus status;
    private int statusCode;
    private T data;
}