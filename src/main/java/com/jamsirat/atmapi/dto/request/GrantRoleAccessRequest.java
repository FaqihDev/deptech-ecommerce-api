package com.jamsirat.atmapi.dto.request;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GrantRoleAccessRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 697096363431663222L;

    private Long roleId;
    private Long userId;

}