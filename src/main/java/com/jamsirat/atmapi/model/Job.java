package com.jamsirat.atmapi.model;


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

    @Column(name = "address")
    private String workAddress;

    @Column(name = "work_at")
    private String workAt;

    @Column(name = "profession")
    private String profession;

    @Column(name = "work_day")
    private String workDay;

    @Column(name = "work_time")
    private String workTime;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
