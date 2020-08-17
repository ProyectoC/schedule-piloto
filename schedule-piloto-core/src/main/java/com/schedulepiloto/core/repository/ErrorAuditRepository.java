package com.schedulepiloto.core.repository;

import com.schedulepiloto.core.entities.model.ErrorAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorAuditRepository extends JpaRepository<ErrorAuditEntity, Long> {
}
