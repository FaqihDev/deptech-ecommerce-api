package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.profile.Domicile;
import com.jamsirat.atmapi.model.profile.UserProfileExtended;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDomicileRepository extends JpaRepository<Domicile,Long> {

    Optional<Domicile> findByUserProfileExtendedId (UserProfileExtended userProfileExtended);
}