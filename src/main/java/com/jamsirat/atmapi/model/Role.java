package com.jamsirat.atmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jamsirat.atmapi.statval.enumeration.EUserRole;
import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
@Entity
@Builder
@Setter
@Getter
public class Role extends BaseMasterData {

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private EUserRole userRole;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(String roleName) {
        super();
    }

    public String getRoleName() {
        return userRole.getName();
    }

}
