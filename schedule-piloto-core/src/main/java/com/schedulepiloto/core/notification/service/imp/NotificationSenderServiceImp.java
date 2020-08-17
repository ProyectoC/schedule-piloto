package com.schedulepiloto.core.notification.service.imp;

import com.schedulepiloto.core.constants.NotificationConstants;
import com.schedulepiloto.core.dto.model.NotificationDto;
import com.schedulepiloto.core.dto.model.NotificationTypeDto;
import com.schedulepiloto.core.notification.service.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.bus.Event;
import reactor.bus.EventBus;

@Component
public class NotificationSenderServiceImp implements NotificationSenderService {

    @Autowired
    private EventBus eventBus;

    @Override
    public void sendValidationNotification(NotificationDto notificationDto) {
        notificationDto.setPersistNotification(false);
        notificationDto.setSendNotification(true);
        notificationDto.setSendType("Validation");
        this.eventBus.notify("notificationConsumer", Event.wrap(notificationDto));
    }

    @Override
    public void sendInformationNotification(NotificationDto notificationDto) {
        notificationDto.setPersistNotification(true);
        notificationDto.setSendNotification(true);
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto(NotificationConstants.NOTIFICATION_TYPE_INFORMATION);
        notificationDto.setNotificationTypeEntity(notificationTypeDto);
        this.eventBus.notify("notificationConsumer", Event.wrap(notificationDto));
    }

    @Override
    public void sendInformationNotificationNotEmail(NotificationDto notificationDto) {
        notificationDto.setPersistNotification(true);
        notificationDto.setSendNotification(false);
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto(NotificationConstants.NOTIFICATION_TYPE_INFORMATION);
        notificationDto.setNotificationTypeEntity(notificationTypeDto);
        this.eventBus.notify("notificationConsumer", Event.wrap(notificationDto));
    }

    @Override
    public void sendSuccessNotification(NotificationDto notificationDto) {
        notificationDto.setPersistNotification(true);
        notificationDto.setSendNotification(true);
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto(NotificationConstants.NOTIFICATION_TYPE_SUCCESS);
        notificationDto.setNotificationTypeEntity(notificationTypeDto);
        this.eventBus.notify("notificationConsumer", Event.wrap(notificationDto));
    }

    @Override
    public void sendWarningNotification(NotificationDto notificationDto) {
        notificationDto.setPersistNotification(true);
        notificationDto.setSendNotification(true);
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto(NotificationConstants.NOTIFICATION_TYPE_WARNING);
        notificationDto.setNotificationTypeEntity(notificationTypeDto);
        this.eventBus.notify("notificationConsumer", Event.wrap(notificationDto));
    }

    @Override
    public void sendErrorNotification(NotificationDto notificationDto) {
        notificationDto.setPersistNotification(true);
        notificationDto.setSendNotification(true);
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto(NotificationConstants.NOTIFICATION_TYPE_ERROR);
        notificationDto.setNotificationTypeEntity(notificationTypeDto);
        this.eventBus.notify("notificationConsumer", Event.wrap(notificationDto));
    }

    @Override
    public void sendAlertNotification(NotificationDto notificationDto) {
        notificationDto.setPersistNotification(true);
        notificationDto.setSendNotification(true);
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto(NotificationConstants.NOTIFICATION_TYPE_ALERT);
        notificationDto.setNotificationTypeEntity(notificationTypeDto);
        this.eventBus.notify("notificationConsumer", Event.wrap(notificationDto));
    }
}
