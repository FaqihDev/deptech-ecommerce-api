package com.jamsirat.atmapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessDeniedException extends RuntimeException implements CustomException {

    private String exceptionMessage;
    private String developerMessage;

}
