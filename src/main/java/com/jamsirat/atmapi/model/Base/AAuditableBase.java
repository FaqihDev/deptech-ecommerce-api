package com.jamsirat.atmapi.model.Base;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AAuditableBase extends BaseMasterData<Long> {


    @CreatedBy
    @Column(name = "created_by")
    protected Long createdBy;


    @CreatedDate
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;


    @LastModifiedBy
    @Column(name = "modified_by")
    protected Long modifiedBy;


    @LastModifiedDate
    @Column(name = "modified_on")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifiedOn;

}