package com.schedulepiloto.core.repository;

import com.schedulepiloto.core.entities.model.NotificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationEntity, Long> {
}
