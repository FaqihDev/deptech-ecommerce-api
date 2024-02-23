package com.jamsirat.atmapi.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RevokeRoleAccessResponse {

    private Long userId;
    private String userName;
    private String message;
}