package com.jamsirat.atmapi.dto.response;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompleteUserProfileResponse {

    private Long userId;
    private String fullName;


}