package com.jamsirat.atmapi.model;

import com.jamsirat.atmapi.statval.enumeration.EDapuanLevel;
import com.jamsirat.atmapi.statval.enumeration.EProgressStatus;
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
public class Status extends BaseMasterData implements Serializable {


    @Column(name = "dapuan")
    private String dapuan;

    @Enumerated(EnumType.STRING)
    @Column(name = "dapuan_level")
    private EDapuanLevel dapuanLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "progress_status")
    private EProgressStatus progressStatus;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


}
