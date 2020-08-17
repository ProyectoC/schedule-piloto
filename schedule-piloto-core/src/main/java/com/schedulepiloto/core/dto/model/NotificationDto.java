package com.schedulepiloto.core.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationDto implements Serializable {

    private Long id;
    private String description;
    private NotificationTypeDto notificationTypeEntity;
    private List<UserNotificationDto> notificationEntities;

    // Just for Logic
    private String sendType;
    private String title;
    private String subject;
    private String content;
    private List<String> emails;
    private Boolean sendNotification;
    private Boolean persistNotification;

    public NotificationDto() {
        this.emails = new ArrayList<>();
    }
}
