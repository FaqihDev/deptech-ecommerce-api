package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.auth.User;
import com.jamsirat.atmapi.model.profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {

    Optional<UserProfile> findByUser(User user);

}