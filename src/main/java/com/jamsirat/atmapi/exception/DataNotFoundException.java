package com.jamsirat.atmapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNotFoundException extends RuntimeException{

    private Integer responseCode;
    private String exceptionMessage;

}
