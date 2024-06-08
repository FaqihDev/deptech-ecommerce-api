package com.jamsirat.atmapi.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotValidException extends RuntimeException {

    private Integer responseCode;
    private String exceptionMessage;
}