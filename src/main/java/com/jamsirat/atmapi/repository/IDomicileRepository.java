package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.profile.Domicile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicileRepository extends JpaRepository<Domicile,Long> {
}