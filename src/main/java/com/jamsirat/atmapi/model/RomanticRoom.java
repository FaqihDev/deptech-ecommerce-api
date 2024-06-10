package com.jamsirat.atmapi.model;

import com.jamsirat.atmapi.model.Base.BaseMasterData;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "romantic_room")
@Entity
@Builder
@Setter
@Getter
public class RomanticRoom extends BaseMasterData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "men_couple")
    private Participant maleCouple;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "women_couple")
    private Participant femaleCouple;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managed_by")
    private Principle managedBy;

}