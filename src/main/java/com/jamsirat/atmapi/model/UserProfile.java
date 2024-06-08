package com.jamsirat.atmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "user_profile")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile extends BaseMasterData implements Serializable {

    @Serial
    private static final long serialVersionUID = -4474084425849609899L;


    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "is_mobile_phone_number_verified")
    private Boolean isMobilePhoneNumberVerified;

    @Column(name = "is_verified_user")
    private Boolean isVerifiedUser;

    @OneToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;

}