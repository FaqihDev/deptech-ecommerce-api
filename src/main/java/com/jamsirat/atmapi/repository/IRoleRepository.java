package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.model.auth.Role;
import com.jamsirat.atmapi.statval.enumeration.EUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {

    Role findByUserRole(EUserRole userRole) throws DataNotFoundException;

    @Query("SELECT r FROM Role r JOIN User u where u.id = :userId")
    Set<Role> findRoleByUserId(Long userId) throws DataNotFoundException;


}