package com.jamsirat.atmapi.model;


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
public class Preference extends BaseMasterData implements Serializable {


    @Enumerated(EnumType.STRING)
    @Column(name = "age_criteria")
    private EAgeCriteria ageCriteria;

    @Column(name = "specific_criteria")
    private String specificCriteria;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "couple_job_criteria")
    private String jobCriteria;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile")
    private UserProfile userProfile;

}
