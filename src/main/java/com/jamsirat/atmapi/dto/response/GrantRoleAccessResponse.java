package com.jamsirat.atmapi.dto.response;

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
    private static final long serialVersionUID = -3520219122397523027L;

    private String message;
    private String roleName;
}