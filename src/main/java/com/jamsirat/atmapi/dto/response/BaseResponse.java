package com.jamsirat.atmapi.dto.response;


import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {
    private static final  long serialVersionUID = -6233145663410668178L;

    private Integer code = 999;
    private String message;
    private T data;
    private String responseCode;

    public BaseResponse(String message, Integer code, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public BaseResponse(String message, Integer code){
        this.code = code;
        this.message = message;
    }

    public BaseResponse(String message) {
        this.message = message;
    }

    public BaseResponse(String responseCode, String message, T data) {
        this.message = message;
        this.data = data;
        this.responseCode = responseCode;
        this.code = null;
    }
}