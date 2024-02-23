package com.jamsirat.atmapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1976685742556762829L;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}