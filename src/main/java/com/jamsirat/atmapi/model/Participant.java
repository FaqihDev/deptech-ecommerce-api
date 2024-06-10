package com.jamsirat.atmapi.model;

import com.jamsirat.atmapi.model.Base.BaseMasterData;
import com.jamsirat.atmapi.model.profile.UserProfileExtended;
import com.jamsirat.atmapi.statval.enumeration.EGender;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Participant")
@Entity
@Builder
@Setter
@Getter
public class Participant extends BaseMasterData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "chosen_by")
    private Long chosenBy;

    @Column(name = "choose_to")
    private Long chooseTo;

    @Column(name = "is_taken")
    private Boolean isTaken;

    @Column(name = "managed_by")
    private Long managedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private EGender gender;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}