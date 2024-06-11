package com.jamsirat.atmapi.dto.request;


import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompleteUserProfileRequest {


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