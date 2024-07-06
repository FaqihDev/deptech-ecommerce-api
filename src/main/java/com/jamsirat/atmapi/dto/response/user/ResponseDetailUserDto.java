package com.jamsirat.atmapi.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetailUserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1386595506033311891L;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
