package com.jamsirat.atmapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "education")
@Entity
@Builder
@Setter
@Getter
public class Education extends BaseMasterData implements Serializable {


    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_majors")
    private String schoolMajors;

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "university_majors")
    private String universityMajors;

    @Column(name = "last_education")
    private String lastEducation;

    @Column(name = "on_going_education")
    private String onGoingEducation;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;



}
