package com.schedulepiloto.core.service;

import com.schedulepiloto.core.dto.model.UserAccountDto;
import com.schedulepiloto.core.dto.model.UserNotificationDto;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import com.schedulepiloto.core.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public interface NotificationLayerService {

    Logger LOGGER = LoggerFactory.getLogger(NotificationLayerService.class);

    // USERS
    void sendNotificationCreateUserAccount(UserAccountDto userAccountDto);

    void sendNotificationActivationUserAccount(UserAccountDto userAccountDto);

//    void sendNotificationVerificationUser(com.acqua.board.coremodule.dto.UserSecurityDTO userSecurityDTO);
//
//    void sendNotificationRestorePasswordUser(com.acqua.board.coremodule.dto.UserSecurityDTO userSecurityDTO,
//                                             String newPassword);
//
//    void sendNotificationChangePasswordUser(com.acqua.board.coremodule.dto.UserSecurityDTO userSecurityDTO);
//
//    // PROJECTS
//    void sendNotificationProjectCreation(UserSecurityDTO userCreator, ProjectDto project, NotificationType type);
//
//    void sendNotificationProjectUpdate(ProjectDto project, List<UserSecurityDTO> listUsers);
//
//    // SPRINTS
//    void sendNotificationSprintCreation(UserSecurityDTO userCreator, SprintDto sprintDto);
//
//    void sendNotificationSprintUpdate(UserSecurityDTO userCreator, SprintDto sprintDto);
//
//    void sendNotificationSprintDaysLeft(AlertSprintDto alertSprintDto);
//
//    // REQUEST
//    void sendNotificationRequestCreation(UserSecurityDTO userCreator, RequestDto requestDto);
//
//    void sendNotificationRequestUpdate(UserSecurityDTO userCreator, RequestDto requestDto);
//
//    // ACTIVITIES
//    void sendNotificationActivityCreation(ActivityDto activityDto);
//
//    void sendNotificationAssignedActivity(ActivityDto activityDto);
//
//    void sendNotificationChangeStatusActivity(StatusDto actualStatus, ActivityStatusLogDto activityStatus);
//
//    void sendNotificationActivityDaysLeft(AlertActivityDto alertActivityDto);
//
//    // ERROR - ACTIVITIES
//    void sendNotificationErrorActivity(ErrorActivityDto errorActivityDto);
//
//    // GET NOTIFICATIONS
//    GroupNotificationLightDto getNotificationDontReceivedByUser(Long idUser) throws AcquaBoardException;
//
//    List<NotificationLightDto> getNotificationDontNotifiedByUser(Long idUser) throws AcquaBoardException;
//
//    String setListNotificationsReceived(List<NotificationLightDto> listNotifications) throws AcquaBoardException;

    static String matchParametersToFileTemplate(String urlTemplate, Map<String, String> parameters) throws SchedulePilotoException {
        try {
            String emailTemplate = CommonUtil.readFile(urlTemplate);
            for (Map.Entry<String, String> pair : parameters.entrySet()) {
                emailTemplate = emailTemplate.replace(pair.getKey(), pair.getValue());
            }
            return emailTemplate;
        } catch (IOException e) {
            throw new SchedulePilotoException("The email template could not be read.");
        }
    }

    static String matchParametersToTemplate(String template, Map<String, String> parameters) {
        for (Map.Entry<String, String> pair : parameters.entrySet()) {
            template = template.replace(pair.getKey(), pair.getValue());
        }
        return template;
    }

    static List<UserNotificationDto> getUsersNotifications(UserAccountDto user) {
        List<UserNotificationDto> list = new ArrayList<>();
        UserNotificationDto userNotification = new UserNotificationDto();
        userNotification.setAccountUserEntity(user);
        list.add(userNotification);
        return list;
    }

    static List<UserNotificationDto> getUsersNotifications(List<UserAccountDto> users) {
        List<UserNotificationDto> list = new ArrayList<>();
        for (UserAccountDto user : users) {
            UserNotificationDto userNotification = new UserNotificationDto();
            userNotification.setAccountUserEntity(user);
            list.add(userNotification);
        }
        return list;
    }
}
