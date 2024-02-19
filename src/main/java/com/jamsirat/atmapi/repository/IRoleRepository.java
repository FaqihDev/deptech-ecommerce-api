package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {

    Role findByUserRole(String roleName) throws DataNotFoundException;
}
