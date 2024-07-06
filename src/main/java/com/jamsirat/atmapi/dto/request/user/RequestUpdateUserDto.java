package com.jamsirat.atmapi.dto.request.user;


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
public class RequestUpdateUserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7470182582239094570L;
    private Long userId;
    private String firstName;
    private String lastName;

}
