package com.jamsirat.atmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public abstract class AAuditableBase {

    @JsonIgnore
    @CreatedBy
    @Column(name = "created_by")
    protected Long createdBy;

    @JsonIgnore
    @CreatedDate
    @Column(name = "created_at")
    protected Date createdAt;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "modified_by")
    protected Long modifiedBy;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "modified_on")
    protected Date modifiedOn;

}
