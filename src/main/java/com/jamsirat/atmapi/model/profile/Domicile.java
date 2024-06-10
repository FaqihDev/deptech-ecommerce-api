package com.jamsirat.atmapi.model.profile;


import com.jamsirat.atmapi.model.Base.BaseMasterData;
import com.jamsirat.atmapi.model.auth.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Domicile")
@Entity
@Builder
@Setter
@Getter
public class Domicile extends BaseMasterData implements Serializable {

    @Column(name = "kelompok_sambung")
    private String kelompokSambung;

    @Column(name = "desa_sambung")
    private String desaSambung;

    @Column(name = "kelompok_address")
    private String kelompokAddress;

    @Column(name = "desa_address")
    private String desaAddress;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}