package com.schedulepiloto.core.notification.service.imp;

import com.schedulepiloto.core.dto.model.NotificationDto;
import com.schedulepiloto.core.notification.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class NotificationConsumer implements Consumer<Event<NotificationDto>> {

    public static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);

    @Autowired
    private NotificationService notificationService;

    @Override
    public void accept(Event<NotificationDto> notificationDtoEvent) {
        NotificationDto notificationDto = notificationDtoEvent.getData();
        try {
            if (notificationDto.getPersistNotification()) {
                NotificationDto notificationNew = this.notificationService.save(notificationDto);
                LOGGER.info("NOTIFICATION PERSISTENCE: OK ---> Notification Id: {}", notificationNew.getId());
            }
            if (notificationDto.getSendNotification()) {
                this.notificationService.initiateNotification(notificationDto);
                LOGGER.info("NOTIFICATION SENDER: OK ---> Notification Subject: {}", notificationDto.getSubject());
            }
        } catch (Exception e) {
            LOGGER.error("ERROR: Generating the sending and storage of the notification: Message: {}", e.getMessage());
        }
    }
}
