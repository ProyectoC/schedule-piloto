package com.schedulepiloto.core.repository;

import com.schedulepiloto.core.entities.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountUserRepository extends JpaRepository<UserAccountEntity, Long> {

    Optional<UserAccountEntity> findByUsername(String username);

    Optional<UserAccountEntity> findByIdentification(String identification);

    Optional<UserAccountEntity> findByIdentificationCode(String identificationCode);

    Optional<UserAccountEntity> findByEmailOrEmailBackup(String email, String emailBackup);
}
