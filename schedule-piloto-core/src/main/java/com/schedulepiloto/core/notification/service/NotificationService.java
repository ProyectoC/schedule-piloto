package com.schedulepiloto.core.notification.service;

import com.schedulepiloto.core.dto.model.NotificationDto;
import com.schedulepiloto.core.dto.model.UserNotificationDto;
import com.schedulepiloto.core.entities.model.NotificationEntity;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface NotificationService {

    ModelMapper modelMapper = new ModelMapper();

    static NotificationEntity convertDTOToEntity(NotificationDto dto) {
        NotificationEntity entity = modelMapper.map(dto, NotificationEntity.class);
        return entity;
    }

    static NotificationDto convertEntityToDTO(NotificationEntity entity) {
        if (entity != null) {
            NotificationDto dto = modelMapper.map(entity, NotificationDto.class);
            return dto;
        } else {
            return null;
        }
    }

    static List<String> getEmails(NotificationDto notificationDto) {
        List<String> emails = new ArrayList<>();
        List<UserNotificationDto> usersNotification = notificationDto.getNotificationEntities();
        for (UserNotificationDto userNotificationDto : usersNotification) {
            if (userNotificationDto.getAccountUserEntity().getEmail() != null) {
                emails.add(userNotificationDto.getAccountUserEntity().getEmail());
            }
        }
        return emails;
    }

    NotificationDto save(NotificationDto notificationDto) throws SchedulePilotoException;

    void initiateNotification(NotificationDto notificationDto) throws SchedulePilotoException;
}
