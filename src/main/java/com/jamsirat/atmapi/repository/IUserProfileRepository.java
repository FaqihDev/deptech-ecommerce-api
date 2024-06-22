package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.auth.User;
import com.jamsirat.atmapi.model.profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {

    @Query("SELECT up FROM UserProfile up where up.user.id = :pUserId")
    Optional<UserProfile> findByUserId(Long pUserId);

}