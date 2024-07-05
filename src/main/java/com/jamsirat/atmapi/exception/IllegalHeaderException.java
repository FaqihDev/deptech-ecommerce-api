package com.jamsirat.atmapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IllegalHeaderException extends RuntimeException implements CustomException{

    private String exceptionMessage;
    private String developerMessage;


}