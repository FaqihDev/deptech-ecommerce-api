package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.model.Role;
import com.jamsirat.atmapi.statval.enumeration.EUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {

    Role findByUserRole(EUserRole userRole) throws DataNotFoundException;

}