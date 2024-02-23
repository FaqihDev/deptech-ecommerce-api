package com.jamsirat.atmapi.model;

import com.jamsirat.atmapi.statval.enumeration.ETokenType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Token extends BaseMasterData implements Serializable {

    @Serial
    private static final long serialVersionUID = 3201884138615687279L;

    @Column(name = "token", unique = true)
    public String token;

    @Column(name = "revoked")
    public boolean isRevoked;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private ETokenType tokenType;

    @Column(name = "is_token_expired")
    private boolean isTokenExpired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}