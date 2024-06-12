package com.jamsirat.atmapi.model.profile;

import com.jamsirat.atmapi.model.Base.AAuditableBase;
import com.jamsirat.atmapi.model.Base.BaseMasterData;
import com.jamsirat.atmapi.model.Participant;
import com.jamsirat.atmapi.model.Principle;
import com.jamsirat.atmapi.statval.enumeration.EGender;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user_profile_extended")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserProfileExtended extends AAuditableBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 5773885358142110306L;


    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "origin")
    private String origin;

    @Column(name = "height")
    private String height;

    @Column(name = "image")
    private String image;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile")
    private UserProfile userProfile;

}