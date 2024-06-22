package com.jamsirat.atmapi.model;


import com.jamsirat.atmapi.model.Base.AAuditableBase;
import com.jamsirat.atmapi.model.Base.BaseMasterData;
import com.jamsirat.atmapi.statval.enumeration.EProgressStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "progress")
@Entity
@Builder
@Setter
@Getter
public class Progress extends AAuditableBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(name = "progress_status")
    private EProgressStatus status;

}