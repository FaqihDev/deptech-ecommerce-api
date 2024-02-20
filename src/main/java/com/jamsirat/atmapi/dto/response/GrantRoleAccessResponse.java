package com.jamsirat.atmapi.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class GrantRoleAccessResponse {
    private String message;
    private String roleName;
}