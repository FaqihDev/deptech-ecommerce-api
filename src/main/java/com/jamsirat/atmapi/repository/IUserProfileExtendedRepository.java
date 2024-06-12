package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.profile.UserProfileExtended;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserProfileExtendedRepository extends JpaRepository<UserProfileExtended,Long> {
}