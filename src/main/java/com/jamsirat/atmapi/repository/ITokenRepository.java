package com.jamsirat.atmapi.repository;

import com.jamsirat.atmapi.model.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITokenRepository extends JpaRepository<Token,Long> {

    @Query("select t from Token t " +
            "INNER JOIN User u on t.user.id = u.id " +
            "WHERE u.id = :userId " +
            "AND (t.isTokenExpired = false or t.isRevoked = false)")
    List<Token> findAllByValidToken(Long userId);

    Optional<Token> findByToken(String token);



}