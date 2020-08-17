package com.schedulepiloto.core.repository;

import com.schedulepiloto.core.entities.model.ParameterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParameterRepository extends CrudRepository<ParameterEntity, Long> {

    Optional<ParameterEntity> findByName(String name);
}
