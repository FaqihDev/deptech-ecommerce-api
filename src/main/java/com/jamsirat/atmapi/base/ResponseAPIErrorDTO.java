package com.jamsirat.atmapi.base;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPIErrorDTO extends AResponseDataDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date timeStamp;
    private HttpStatus status;
    private String exception;
    @Deprecated
    private String message;
    private String path;
    private String error;

    @Override
    public Integer getResponseCode() {
        return getStatus().value();
    }


    public String getResponseMessage() {
        return getMessage();
    }

}