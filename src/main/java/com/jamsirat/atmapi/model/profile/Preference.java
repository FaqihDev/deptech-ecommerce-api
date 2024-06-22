package com.jamsirat.atmapi.model.profile;


import com.jamsirat.atmapi.model.Base.AAuditableBase;
import com.jamsirat.atmapi.model.Base.BaseMasterData;
import com.jamsirat.atmapi.statval.enumeration.EAgeCriteria;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "preference")
@Entity
@Builder
@Setter
@Getter
public class Preference extends AAuditableBase implements Serializable {


    @Enumerated(EnumType.STRING)
    @Column(name = "age_criteria")
    private EAgeCriteria ageCriteria;

    @Column(name = "specific_criteria")
    private String specificCriteria;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "couple_job_criteria")
    private String jobCriteria;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}