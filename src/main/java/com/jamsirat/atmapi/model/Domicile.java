package com.jamsirat.atmapi.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Domicile")
@Entity
@Builder
@Setter
@Getter
public class Domicile extends BaseMasterData implements Serializable {


    @Column(name = "kelompok_sambung")
    private String kelompokSambung;

    @Column(name = "desa_sambung")
    private String desaSambung;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "kelompok_address")
    private String kelompokAddress;

    @Column(name = "desaAddress")
    private String desaAddress;

}
