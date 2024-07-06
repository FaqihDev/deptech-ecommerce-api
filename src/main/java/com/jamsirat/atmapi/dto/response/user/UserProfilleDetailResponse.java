package com.jamsirat.atmapi.dto.response.user;


import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfilleDetailResponse {

    private Long userId;
    private String fullName;
    private String birthPlace;
    private LocalDate birthDate;
    private String gender;
    private String height;
    private String address;
    private String phoneNumber;
    private String origin;
    private String kelompokSambung;
    private String desaSambung;
    private String kelompokAddress;
    private String desaAddress;

}