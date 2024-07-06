package com.jamsirat.atmapi.dto.response.user;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class GrantRoleAccessResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 6223339341729736414L;

    private String message;
    private String roleName;
}