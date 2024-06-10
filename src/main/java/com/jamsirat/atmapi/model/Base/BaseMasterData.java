package com.jamsirat.atmapi.model.Base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseMasterData extends AAuditableBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 2252590980362157575L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    protected Long id;

    @Column(name = "is_deleted")
    protected Boolean isDeleted;
}