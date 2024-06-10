package com.jamsirat.atmapi.model;


import com.jamsirat.atmapi.model.Base.BaseMasterData;
import com.jamsirat.atmapi.model.profile.UserProfileExtended;
import com.jamsirat.atmapi.statval.enumeration.EDapuanLevel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Principle")
@Entity
@Builder
@Setter
@Getter
public class Principle extends BaseMasterData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "dapuan_level")
    @Enumerated(EnumType.STRING)
    private EDapuanLevel dapuanLevel;


    @OneToMany(mappedBy = "managedBy",cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, orphanRemoval = true)
    List<RomanticRoom> romanticRoomId;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}