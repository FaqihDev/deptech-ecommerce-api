package com.jamsirat.atmapi.dto.response;

import com.jamsirat.atmapi.model.auth.Role;
import jakarta.mail.search.SearchTerm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetailUserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1386595506033311891L;
    private Long userId;
    private String name;
    private String email;
    private String role;
}
