package com.jamsirat.atmapi.model.profile;


import com.jamsirat.atmapi.model.Base.BaseMasterData;
import com.jamsirat.atmapi.model.auth.User;
import com.jamsirat.atmapi.statval.enumeration.EJobStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job")
@Entity
@Builder
@Setter
@Getter
public class Job extends BaseMasterData implements Serializable {


    @Enumerated(EnumType.STRING)
    @Column(name = "job_status")
    private EJobStatus jobStatus;

    @Column(name = "office_address")
    private String office_address;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "profession")
    private String profession;

    @Column(name = "work_day")
    private String workDay;

    @Column(name = "work_time")
    private String workTime;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}