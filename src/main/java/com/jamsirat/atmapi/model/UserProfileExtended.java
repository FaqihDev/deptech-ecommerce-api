package com.jamsirat.atmapi.model;

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
public class UserProfileExtended implements Serializable {

    @Serial
    private static final long serialVersionUID = 5773885358142110306L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1,initialValue = 1,name = "user_profile_extended_id_seq")
    @Column(name = "id")
    private Long id;

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


    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile")
    private UserProfile userProfile;

}