package com.schedulepiloto.core.repository;

import com.schedulepiloto.core.entities.model.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query("SELECT u from TokenEntity u INNER JOIN u.tokenTypeEntity ar WHERE u.id = :id AND ar.name = 'Activate User'")
    Optional<TokenEntity> findByIdAndActivateUser(Long id);
}
