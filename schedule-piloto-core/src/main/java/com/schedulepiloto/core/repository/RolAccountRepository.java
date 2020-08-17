package com.schedulepiloto.core.repository;

import com.schedulepiloto.core.entities.model.RolAccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolAccountRepository extends CrudRepository<RolAccountEntity, Long> {
}
