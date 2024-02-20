package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.Role;
import com.jamsirat.atmapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String username);

    Set<Role> findRolesById(@Param("userId") Long userId);
}