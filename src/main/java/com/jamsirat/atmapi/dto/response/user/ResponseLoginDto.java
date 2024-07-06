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
public class ResponseLoginDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1386595506033311891L;
    private String name;
    private String email;
    private String accessToken;
    private String refreshToken;
    private String role;
}