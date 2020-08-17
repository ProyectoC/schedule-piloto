package com.schedulepiloto.core.repository;

import com.schedulepiloto.core.entities.model.TokenTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenTypeRepository extends CrudRepository<TokenTypeEntity, Long> {
}
