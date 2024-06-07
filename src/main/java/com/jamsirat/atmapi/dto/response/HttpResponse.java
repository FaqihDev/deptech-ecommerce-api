package com.jamsirat.atmapi.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Data
@Builder
public class HttpResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -8781718059336021092L;

    private String timeStamp;
    private String developerMessage;
    private String message;
    private HttpStatus status;
    private int statusCode;
    private T data;
}