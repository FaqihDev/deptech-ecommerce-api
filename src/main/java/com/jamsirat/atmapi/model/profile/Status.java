package com.jamsirat.atmapi.model.profile;

import com.jamsirat.atmapi.model.Base.AAuditableBase;
import com.jamsirat.atmapi.model.Base.BaseMasterData;
import com.jamsirat.atmapi.model.auth.User;
import com.jamsirat.atmapi.statval.enumeration.EDapuanLevel;
import com.jamsirat.atmapi.statval.enumeration.EStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Status")
@Entity
@Builder
@Setter
@Getter
public class Status extends AAuditableBase implements Serializable {


    @Column(name = "dapuan")
    private String dapuan;

    @Enumerated(EnumType.STRING)
    @Column(name = "dapuan_level")
    private EDapuanLevel dapuanLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EStatus status;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}