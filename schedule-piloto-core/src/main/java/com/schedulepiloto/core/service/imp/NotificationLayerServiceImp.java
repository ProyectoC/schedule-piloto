package com.schedulepiloto.core.service.imp;

import com.schedulepiloto.core.config.NotificationConfig;
import com.schedulepiloto.core.constants.AccountUserConstants;
import com.schedulepiloto.core.dto.model.NotificationDto;
import com.schedulepiloto.core.dto.model.UserAccountDto;
import com.schedulepiloto.core.email.constants.EmailConstants;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import com.schedulepiloto.core.notification.service.NotificationSenderService;
import com.schedulepiloto.core.service.GlobalListDinamicService;
import com.schedulepiloto.core.service.NotificationLayerService;
import com.schedulepiloto.core.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component("bean1")
public class NotificationLayerServiceImp implements NotificationLayerService {

    @Autowired
    private GlobalListDinamicService globalListDinamicService;

    @Autowired
    private NotificationConfig notificationConfig;

    @Autowired
    private NotificationSenderService notificationSenderService;

//    @Autowired
//    private UserNotificationService userNotificationService;

//    @Autowired
//    private UserService userService;

//    @Autowired
//    private QueryService queryService;

//    @Autowired
//    private GlobalListDinamic<StatusDto> globalStatusList;


    @Async
    @Override
    public void sendNotificationCreateUserAccount(UserAccountDto userNew) {
        // Get Template
        Path filePath = Paths.get(this.notificationConfig.getCommon().getPathFiles(), EmailConstants.EMAIL_SEND_REGISTER_USER);
        String templateClient = filePath.toString();

        // Get Properties
        String urlWebApi = this.globalListDinamicService.getParameterValueEmpty(EmailConstants.PARAMETER_WEB_API);

        // Build Parameters
        Map<String, String> parameters = new HashMap<>();
        parameters.put(EmailConstants.PARAMETER_TEMPLATE_USER_NAME, userNew.getFirstName());
        parameters.put(EmailConstants.PARAMETER_TEMPLATE_URL_VERIFICATION, urlWebApi + AccountUserConstants.REST_PATH_DEFAULT_V1 + AccountUserConstants.ACTIVATE_USER_ACCOUNT_REST
                + "?tk=" + userNew.getActivationTokenEntity().getKey() + "&id=" + userNew.getId());

        // Build Notification
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEmails(Arrays.asList(userNew.getEmail()));
        notificationDto.setSubject(EmailConstants.SUBJECT_DEFAULT_SEND_REGISTER_USER);
        try {
            notificationDto.setContent(NotificationLayerService.matchParametersToFileTemplate(templateClient, parameters));
            this.notificationSenderService.sendValidationNotification(notificationDto);
        } catch (SchedulePilotoException ex) {
            LOGGER.error("Could not send validation notification user. Error: {}", ex.getMessage());
        }

        filePath = Paths.get(this.notificationConfig.getCommon().getPathFiles(), EmailConstants.EMAIL_SEND_REGISTER_USER_US);
        templateClient = filePath.toString();

        parameters = new HashMap<>();
        parameters.put(EmailConstants.PARAMETER_TEMPLATE_SUB_TITLE, EmailConstants.SUBJECT_DEFAULT_SEND_REGISTER_USER_US);
        parameters.put(EmailConstants.PARAMETER_TEMPLATE_USER_NAME, userNew.getUsername());
        parameters.put(EmailConstants.PARAMETER_TEMPLATE_USER_EMAIL, userNew.getEmail());

        notificationDto = new NotificationDto();
        notificationDto.setEmails(Arrays.asList(notificationConfig.getCommon().getUs()));
        notificationDto.setSubject(EmailConstants.SUBJECT_DEFAULT_SEND_REGISTER_USER_US);
        try {
            notificationDto.setContent(NotificationLayerService.matchParametersToFileTemplate(templateClient, parameters));
            this.notificationSenderService.sendValidationNotification(notificationDto);
        } catch (SchedulePilotoException ex) {
            LOGGER.error("Could not send notification to Myventory CEO. Error: {}", ex.getMessage());
        }
    }

    @Async
    @Override
    public void sendNotificationActivationUserAccount(UserAccountDto userAccountDto) {
        // Get Template
        Path filePath = Paths.get(this.notificationConfig.getCommon().getPathFiles(), EmailConstants.EMAIL_SEND_VALIDATION_USER);
        String templateClient = filePath.toString();

        // Get Properties
        String urlAppWeb = this.globalListDinamicService.getParameterValueEmpty(EmailConstants.PARAMETER_URL_APP);

        // Build Parameters
        Map<String, String> parameters = new HashMap<>();
        parameters.put(EmailConstants.PARAMETER_TEMPLATE_USER_NAME, userAccountDto.getFirstName());
        parameters.put(EmailConstants.PARAMETER_TEMPLATE_URL_LOGIN, urlAppWeb + CommonUtil.END_POINT_LOGIN_WEB);

        // Build Notification
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEmails(Arrays.asList(userAccountDto.getEmail()));
        notificationDto.setSubject(EmailConstants.SUBJECT_DEFAULT_SEND_RESPONSE_VALIDATION_EMAIL);
        try {
            notificationDto.setContent(NotificationLayerService.matchParametersToFileTemplate(templateClient, parameters));
            this.notificationSenderService.sendValidationNotification(notificationDto);
        } catch (SchedulePilotoException ex) {
            LOGGER.error("Could not send verification notification user. Error: {}", ex.getMessage());
        }
    }
//
//    @Async
//    @Override
//    public void sendNotificationRestorePasswordUser(com.acqua.board.coremodule.dto.UserSecurityDTO userNew, String newPassword) {
//        Path filePath = Paths.get(appPropertiesService.getFilePath(), EmailConstants.EMAIL_SEND_FORGOT_PASSWORD_USER);
//        String templateClient = filePath.toString();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put(EmailConstants.PARAMETER_TEMPLATE_USER_NAME, userNew.getFirstName());
//        parameters.put(EmailConstants.PARAMETER_TEMPLATE_USER_PASSWORD, newPassword);
//        NotificationDto notificationDto = new NotificationDto();
//        notificationDto.setEmails(Arrays.asList(userNew.getEmail()));
//        notificationDto.setSubject(EmailConstants.SUBJECT_DEFAULT_SEND_REMEMBER_PASSWORD);
//        try {
//            notificationDto.setContent(NotificationLayerService.matchParametersToFileTemplate(templateClient, parameters));
//            this.notificationSenderService.sendValidationNotification(notificationDto);
//        } catch (AcquaBoardException ex) {
//            LOGGER.error("Could not send notification restore user. Error: {}", ex.getMessage());
//        }
//    }
//
//    @Async
//    @Override
//    public void sendNotificationChangePasswordUser(com.acqua.board.coremodule.dto.UserSecurityDTO userNew) {
//        Path filePath = Paths.get(appPropertiesService.getFilePath(), EmailConstants.EMAIL_SEND_CHANGE_PASSWORD_USER);
//        String templateClient = filePath.toString();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put(EmailConstants.PARAMETER_TEMPLATE_USER_NAME, userNew.getFirstName());
//        NotificationDto notificationDto = new NotificationDto();
//        notificationDto.setEmails(Arrays.asList(userNew.getEmail()));
//        notificationDto.setSubject(EmailConstants.SUBJECT_DEFAULT_SEND_CHANGE_PASSWORD);
//        try {
//            notificationDto.setContent(NotificationLayerService.matchParametersToFileTemplate(templateClient, parameters));
//            this.notificationSenderService.sendValidationNotification(notificationDto);
//        } catch (AcquaBoardException ex) {
//            LOGGER.error("Could not send notification change password user. Error: {}", ex.getMessage());
//        }
//    }
//
//    @Async
//    @Override
//    public void sendNotificationProjectCreation(UserSecurityDTO userCreator, ProjectDto project, NotificationType type) {
//        if (type == NotificationType.INFORMATION) {
//            List<ProjectUserDto> listUsersFromProject = project.getProjectUserEntities();
//            List<UserSecurityDTO> listUsers = new ArrayList<>();
//            String listSimpleNamesUsers = "";
//            for (ProjectUserDto userTemp : listUsersFromProject) {
//                UserSecurityDTO user = userTemp.getUserSecurityEntity();
//                user = this.userService.getOne(user.getId());
//                listUsers.add(user);
//                listSimpleNamesUsers = listSimpleNamesUsers + user.getUserName() + ", ";
//            }
//            if (listSimpleNamesUsers.equals("")) {
//                listSimpleNamesUsers = "NO SE AGREGARON INTEGRANTES.";
//            }
//            NotificationDto notificationDto = new NotificationDto();
//            String description = NotificationTemplate.TEMPLATE_INFORMATION_CREATION_PROJECT.getDescription();
//            Map<String, String> parameters = new HashMap<>();
//            parameters.put("[USER_NAME]", userCreator.getFirstName());
//            parameters.put("[PROJECT_NAME]", project.getName());
//            parameters.put("[LIST_USERS]", listSimpleNamesUsers);
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_CREATION_PROJECT.getTitle());
//            notificationDto.setSubject(NotificationTemplate.TEMPLATE_INFORMATION_CREATION_PROJECT.getSubject());
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(userCreator));
//            this.notificationSenderService.sendInformationNotification(notificationDto);
//
//            notificationDto = new NotificationDto();
//            description = NotificationTemplate.TEMPLATE_INFORMATION_ADD_USER_TO_PROJECT.getDescription();
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_ADD_USER_TO_PROJECT.getTitle());
//            notificationDto.setSubject(NotificationTemplate.TEMPLATE_INFORMATION_ADD_USER_TO_PROJECT.getSubject());
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(listUsers));
//            this.notificationSenderService.sendInformationNotification(notificationDto);
//        }
//    }
//
//    @Async
//    @Override
//    public void sendNotificationProjectUpdate(ProjectDto project, List<UserSecurityDTO> listUsers) {
//        if (listUsers.isEmpty())
//            return;
//        List<UserSecurityDTO> listUsersNew = new ArrayList<>();
//        String listSimpleNamesUsers = "";
//        for (UserSecurityDTO userTemp : listUsers) {
//            userTemp = this.userService.getOne(userTemp.getId());
//            listUsersNew.add(userTemp);
//            listSimpleNamesUsers = listSimpleNamesUsers + userTemp.getUserName() + ", ";
//        }
//
//        UserSecurityDTO userCreator = this.userService.getOne(project.getUserSecurityEntity().getId());
//        NotificationDto notificationDto = new NotificationDto();
//        String description = NotificationTemplate.TEMPLATE_INFORMATION_UPDATE_PROJECT.getDescription();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[USER_NAME]", userCreator.getFirstName());
//        parameters.put("[PROJECT_NAME]", project.getName());
//        parameters.put("[LIST_USERS]", listSimpleNamesUsers);
//        description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//        notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_UPDATE_PROJECT.getTitle());
//        notificationDto.setSubject(NotificationTemplate.TEMPLATE_INFORMATION_UPDATE_PROJECT.getSubject());
//        notificationDto.setDescription(description);
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(userCreator));
//        this.notificationSenderService.sendInformationNotification(notificationDto);
//
//        notificationDto = new NotificationDto();
//        description = NotificationTemplate.TEMPLATE_INFORMATION_ADD_USER_TO_PROJECT.getDescription();
//        description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//        notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_ADD_USER_TO_PROJECT.getTitle());
//        notificationDto.setSubject(NotificationTemplate.TEMPLATE_INFORMATION_ADD_USER_TO_PROJECT.getSubject());
//        notificationDto.setDescription(description);
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(listUsersNew));
//        this.notificationSenderService.sendInformationNotification(notificationDto);
//    }
//
//    @Async
//    @Override
//    public void sendNotificationSprintCreation(UserSecurityDTO userCreator, SprintDto sprintDto) {
//        NotificationDto notificationDto = new NotificationDto();
//        String description = NotificationTemplate.TEMPLATE_INFORMATION_CREATION_SPRINT.getDescription();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[USER_NAME]", userCreator.getFirstName());
//        parameters.put("[SPRINT_NAME]", sprintDto.getName());
//        description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//        notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_CREATION_SPRINT.getTitle());
//        notificationDto.setSubject(NotificationTemplate.TEMPLATE_INFORMATION_CREATION_SPRINT.getSubject());
//        notificationDto.setDescription(description);
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(userCreator));
//        this.notificationSenderService.sendInformationNotificationNotEmail(notificationDto);
//    }
//
//    @Async
//    @Override
//    public void sendNotificationSprintUpdate(UserSecurityDTO userCreator, SprintDto sprintDto) {
//        NotificationDto notificationDto = new NotificationDto();
//        Optional<StatusDto> statusOptionalDto = this.globalStatusList.getItems().stream().filter(parameter ->
//                parameter.getId().equals(sprintDto.getStatusEntity().getId())).findFirst();
//        StatusDto statusDto;
//        statusDto = statusOptionalDto.orElseGet(sprintDto::getStatusEntity);
//        String description = NotificationTemplate.TEMPLATE_WARNING_UPDATE_SPRINT.getDescription();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[USER_NAME]", userCreator.getFirstName() != null ? userCreator.getFirstName() : "");
//        parameters.put("[SPRINT_NAME]", sprintDto.getName() != null ? sprintDto.getName() : "");
//        parameters.put("[DATE_STARTED]", sprintDto.getStartDate() != null ? sprintDto.getStartDate().toString() : "");
//        parameters.put("[WEEKS]", sprintDto.getWeeks() + "");
//        parameters.put("[STATUS]", statusDto.getName() != null ? statusDto.getName() : "");
//        description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//        notificationDto.setTitle(NotificationTemplate.TEMPLATE_WARNING_UPDATE_SPRINT.getTitle());
//        notificationDto.setSubject(NotificationTemplate.TEMPLATE_WARNING_UPDATE_SPRINT.getSubject());
//        notificationDto.setDescription(description);
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(userCreator));
//        this.notificationSenderService.sendWarningNotification(notificationDto);
//    }
//
//    @Async
//    @Transactional
//    @Override
//    public void sendNotificationSprintDaysLeft(AlertSprintDto alertSprintDto) {
//        NotificationTemplate notificationTemplate;
//        if (alertSprintDto.getDaysLeft().compareTo(BigInteger.ZERO) < 0)
//            notificationTemplate = NotificationTemplate.TEMPLATE_ALERT_SPRINT_DAYS_AFTER;
//        else
//            notificationTemplate = NotificationTemplate.TEMPLATE_ALERT_SPRINT_DAYS_LEFT;
//        NotificationDto notificationDto = new NotificationDto();
//        String description = notificationTemplate.getDescription();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[USER_NAME]", alertSprintDto.getNameUser());
//        parameters.put("[NAME_SPRINT]", alertSprintDto.getNameSprint());
//        parameters.put("[DAYS_LEFT]", alertSprintDto.getDaysLeft().abs() + " dia(s)");
//        parameters.put("[STATUS_SPRINT]", alertSprintDto.getStatusName());
//        parameters.put("[MESSAGE]", alertSprintDto.getStatusDescription());
//        description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//        notificationDto.setTitle(notificationTemplate.getTitle());
//        notificationDto.setSubject(notificationTemplate.getSubject());
//        notificationDto.setDescription(description);
//        UserSecurityDTO user = new UserSecurityDTO(alertSprintDto.getIdUser().longValue());
//        user.setEmail(alertSprintDto.getEmailUser());
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(user));
//        this.notificationSenderService.sendAlertNotification(notificationDto);
//    }
//
//    @Async
//    @Override
//    public void sendNotificationRequestCreation(UserSecurityDTO userCreator, RequestDto requestDto) {
//        NotificationDto notificationDto = new NotificationDto();
//        String description = NotificationTemplate.TEMPLATE_INFORMATION_CREATION_REQUEST.getDescription();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[USER_NAME]", userCreator.getFirstName());
//        parameters.put("[REQUEST_NAME]", requestDto.getName());
//        description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//        notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_CREATION_REQUEST.getTitle());
//        notificationDto.setSubject(NotificationTemplate.TEMPLATE_INFORMATION_CREATION_REQUEST.getSubject());
//        notificationDto.setDescription(description);
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(userCreator));
//        this.notificationSenderService.sendInformationNotificationNotEmail(notificationDto);
//    }
//
//    @Async
//    @Override
//    public void sendNotificationRequestUpdate(UserSecurityDTO userCreator, RequestDto requestDto) {
//        NotificationDto notificationDto = new NotificationDto();
//        String description = NotificationTemplate.TEMPLATE_INFORMATION_UPDATE_REQUEST.getDescription();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[USER_NAME]", userCreator.getFirstName() != null ? userCreator.getFirstName() : "");
//        parameters.put("[REQUEST_NAME]", requestDto.getName());
//        description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//        notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_UPDATE_REQUEST.getTitle());
//        notificationDto.setSubject(NotificationTemplate.TEMPLATE_INFORMATION_UPDATE_REQUEST.getSubject());
//        notificationDto.setDescription(description);
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(userCreator));
//        this.notificationSenderService.sendInformationNotificationNotEmail(notificationDto);
//    }
//
//    @Async
//    @Override
//    public void sendNotificationActivityCreation(ActivityDto activityDto) {
//        NotificationDto notificationDto = new NotificationDto();
//        String description = NotificationTemplate.TEMPLATE_INFORMATION_CREATION_ACTIVITY.getDescription();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[USER_NAME]", activityDto.getUserCreationSecurityEntity().getFirstName());
//        parameters.put("[ACTIVITY_NAME]", activityDto.getName());
//        description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//        notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_CREATION_ACTIVITY.getTitle());
//        notificationDto.setSubject(NotificationTemplate.TEMPLATE_INFORMATION_CREATION_ACTIVITY.getSubject());
//        notificationDto.setDescription(description);
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(activityDto.getUserCreationSecurityEntity()));
//        this.notificationSenderService.sendInformationNotificationNotEmail(notificationDto);
//    }
//
//    @Async
//    @Override
//    public void sendNotificationAssignedActivity(ActivityDto activityDto) {
//        UserSecurityDTO userResponsible = this.userService.getOne(activityDto.getUserResponsibleSecurityEntity().getId());
//        List<UserSkillDto> skillsUser = userResponsible.getUserSkillEntities();
//        boolean userHaveSkill = false;
//        for (UserSkillDto skillTemp : skillsUser) {
//            if (skillTemp.getSkillEntity().getId().equals(activityDto.getSkillEntity().getId())) {
//                userHaveSkill = true;
//                break;
//            }
//        }
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[ACTIVITY_NAME]", activityDto.getName());
//        parameters.put("[USER_RESPONSIBLE]", userResponsible.getFirstName());
//        NotificationDto notificationDto = new NotificationDto();
//        String description = NotificationTemplate.TEMPLATE_INFORMATION_ASSIGNED_ACTIVITY.getDescription();
//        description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//        notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_ASSIGNED_ACTIVITY.getTitle());
//        notificationDto.setSubject(NotificationTemplate.TEMPLATE_INFORMATION_ASSIGNED_ACTIVITY.getSubject());
//        notificationDto.setDescription(description);
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(Arrays.asList(activityDto.getUserCreationSecurityEntity(), userResponsible)));
//        this.notificationSenderService.sendInformationNotification(notificationDto);
//        if (!userHaveSkill) {
//            notificationDto = new NotificationDto();
//            description = NotificationTemplate.TEMPLATE_WARNING_ASSIGNED_ACTIVITY_LEADER.getDescription();
//            parameters.put("[USER_LEADER]", activityDto.getUserCreationSecurityEntity().getFirstName());
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_WARNING_ASSIGNED_ACTIVITY_LEADER.getTitle());
//            notificationDto.setSubject(NotificationTemplate.TEMPLATE_WARNING_ASSIGNED_ACTIVITY_LEADER.getSubject());
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(activityDto.getUserCreationSecurityEntity()));
//            this.notificationSenderService.sendWarningNotification(notificationDto);
//        }
//    }
//
//    @Async
//    @Override
//    public void sendNotificationChangeStatusActivity(StatusDto actualStatus, ActivityStatusLogDto activityStatus) {
//        ActivityDto activityDto = activityStatus.getActivityEntity();
//        UserSecurityDTO userCreator = activityStatus.getActivityEntity().getUserCreationSecurityEntity();
//        String activityName = activityStatus.getActivityEntity().getName();
//        String description;
//        String subject;
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[ACTIVITY_NAME]", activityName);
//        NotificationDto notificationDto = new NotificationDto();
//        // STATUS: BLOCKED ---> TO DO
//        if (actualStatus.getId().equals(ActivityConstants.STATUS_BLOCKED) &&
//                activityStatus.getStatusEntity().getId().equals(ActivityConstants.STATUS_TO_DO)) {
//            description = NotificationTemplate.TEMPLATE_WARNING_ACTIVITY_BLOCKED_DO.getDescription();
//            subject = NotificationTemplate.TEMPLATE_WARNING_ACTIVITY_BLOCKED_DO.getSubject();
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_WARNING_ACTIVITY_BLOCKED_DO.getTitle());
//            notificationDto.setSubject(subject);
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(userCreator));
//            this.notificationSenderService.sendWarningNotification(notificationDto);
//            return;
//        }
//        UserSecurityDTO userResponsible = this.userService.getOne(activityDto.getUserResponsibleSecurityEntity().getId());
//        // STATUS: TO DO ---> DOING
//        if (actualStatus.getId().equals(ActivityConstants.STATUS_TO_DO) &&
//                activityStatus.getStatusEntity().getId().equals(ActivityConstants.STATUS_DOING)) {
//            description = NotificationTemplate.TEMPLATE_INFORMATION_ACTIVITY_DO_DOING.getDescription();
//            subject = NotificationTemplate.TEMPLATE_INFORMATION_ACTIVITY_DO_DOING.getSubject();
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_ACTIVITY_DO_DOING.getTitle());
//            notificationDto.setSubject(subject);
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(Arrays.asList(userCreator,
//                    userResponsible)));
//            this.notificationSenderService.sendInformationNotification(notificationDto);
//            return;
//        }
//        // STATUS: TO DO ---> BLOCKED
//        if (actualStatus.getId().equals(ActivityConstants.STATUS_TO_DO) &&
//                activityStatus.getStatusEntity().getId().equals(ActivityConstants.STATUS_BLOCKED)) {
//            description = NotificationTemplate.TEMPLATE_ERROR_ACTIVITY_DO_BLOCKED.getDescription();
//            subject = NotificationTemplate.TEMPLATE_ERROR_ACTIVITY_DO_BLOCKED.getSubject();
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_ERROR_ACTIVITY_DO_BLOCKED.getTitle());
//            notificationDto.setSubject(subject);
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(
//                    Arrays.asList(activityStatus.getActivityEntity().getUserCreationSecurityEntity(),
//                            userResponsible)));
//            this.notificationSenderService.sendErrorNotification(notificationDto);
//            return;
//        }
//        // STATUS: DOING ---> DOING
//        if (actualStatus.getId().equals(ActivityConstants.STATUS_DOING) &&
//                activityStatus.getStatusEntity().getId().equals(ActivityConstants.STATUS_DOING)) {
//            description = NotificationTemplate.TEMPLATE_INFORMATION_HOURS_WORKED_ACTIVITY.getDescription();
//            subject = NotificationTemplate.TEMPLATE_INFORMATION_HOURS_WORKED_ACTIVITY.getSubject();
//            parameters.put("[HOURS]", activityStatus.getHours() + "");
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_HOURS_WORKED_ACTIVITY.getTitle());
//            notificationDto.setSubject(subject);
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(Arrays.asList(userCreator,
//                    userResponsible)));
//            this.notificationSenderService.sendInformationNotification(notificationDto);
//            return;
//        }
//        // STATUS: DOING ---> COMPLETE
//        if (actualStatus.getId().equals(ActivityConstants.STATUS_DOING) &&
//                activityStatus.getStatusEntity().getId().equals(ActivityConstants.STATUS_COMPLETE)) {
//            description = NotificationTemplate.TEMPLATE_INFORMATION_ACTIVITY_DOING_COMPLETE.getDescription();
//            subject = NotificationTemplate.TEMPLATE_INFORMATION_ACTIVITY_DOING_COMPLETE.getSubject();
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_INFORMATION_ACTIVITY_DOING_COMPLETE.getTitle());
//            notificationDto.setSubject(subject);
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(Arrays.asList(userCreator,
//                    userResponsible)));
//            this.notificationSenderService.sendInformationNotification(notificationDto);
//            return;
//        }
//        // STATUS: DOING ---> BLOCKED
//        if (actualStatus.getId().equals(ActivityConstants.STATUS_DOING) &&
//                activityStatus.getStatusEntity().getId().equals(ActivityConstants.STATUS_BLOCKED)) {
//            description = NotificationTemplate.TEMPLATE_WARNING_ACTIVITY_DOING_BLOCKED.getDescription();
//            subject = NotificationTemplate.TEMPLATE_WARNING_ACTIVITY_DOING_BLOCKED.getSubject();
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_WARNING_ACTIVITY_DOING_BLOCKED.getTitle());
//            notificationDto.setSubject(subject);
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(
//                    Arrays.asList(activityStatus.getActivityEntity().getUserCreationSecurityEntity(),
//                            userResponsible)));
//            this.notificationSenderService.sendWarningNotification(notificationDto);
//            return;
//        }
//        // STATUS: COMPLETE ---> VALIDATE
//        if (actualStatus.getId().equals(ActivityConstants.STATUS_COMPLETE) &&
//                activityStatus.getStatusEntity().getId().equals(ActivityConstants.STATUS_VALIDATE)) {
//            description = NotificationTemplate.TEMPLATE_SUCCESS_ACTIVITY_COMPLETE_VALIDATE.getDescription();
//            subject = NotificationTemplate.TEMPLATE_SUCCESS_ACTIVITY_COMPLETE_VALIDATE.getSubject();
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_SUCCESS_ACTIVITY_COMPLETE_VALIDATE.getTitle());
//            notificationDto.setSubject(subject);
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(
//                    Arrays.asList(activityStatus.getActivityEntity().getUserCreationSecurityEntity(),
//                            userResponsible)));
//            this.notificationSenderService.sendSuccessNotification(notificationDto);
//        }
//        // STATUS: COMPLETE ---> TO DO
//        if (actualStatus.getId().equals(ActivityConstants.STATUS_COMPLETE) &&
//                activityStatus.getStatusEntity().getId().equals(ActivityConstants.STATUS_TO_DO)) {
//            description = NotificationTemplate.TEMPLATE_ALERT_ACTIVITY_COMPLETE_DOING.getDescription();
//            subject = NotificationTemplate.TEMPLATE_ALERT_ACTIVITY_COMPLETE_DOING.getSubject();
//            description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//            notificationDto.setTitle(NotificationTemplate.TEMPLATE_ALERT_ACTIVITY_COMPLETE_DOING.getTitle());
//            notificationDto.setSubject(subject);
//            notificationDto.setDescription(description);
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(
//                    Arrays.asList(activityStatus.getActivityEntity().getUserCreationSecurityEntity(),
//                            userResponsible)));
//            this.notificationSenderService.sendAlertNotification(notificationDto);
//        }
//    }
//
//    @Async
//    @Transactional
//    @Override
//    public void sendNotificationActivityDaysLeft(AlertActivityDto alertActivityDto) {
//        NotificationDto notificationDto = new NotificationDto();
//        NotificationTemplate notificationTemplate;
//        if (alertActivityDto.getDaysLeft().compareTo(BigInteger.ZERO) < 0)
//            notificationTemplate = NotificationTemplate.TEMPLATE_ALERT_ACTIVITY_DAYS_AFTER;
//        else
//            notificationTemplate = NotificationTemplate.TEMPLATE_ALERT_ACTIVITY_DAYS_LEFT;
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[NAME_ACTIVITY]", alertActivityDto.getNameActivity());
//        parameters.put("[DAYS_LEFT]", alertActivityDto.getDaysLeft().abs() + " dia(s)");
//        parameters.put("[STATUS_ACTIVITY]", alertActivityDto.getStatusName());
//        parameters.put("[MESSAGE]", alertActivityDto.getStatusDescription());
//        String description = NotificationLayerService.matchParametersToTemplate(notificationTemplate.getDescription(), parameters);
//        notificationDto.setTitle(notificationTemplate.getTitle());
//        notificationDto.setSubject(notificationTemplate.getSubject());
//        notificationDto.setDescription(description);
//        UserSecurityDTO userCreator = new UserSecurityDTO(alertActivityDto.getIdUserCreator().longValue());
//        userCreator.setEmail(alertActivityDto.getEmailUserCreator());
//        if (alertActivityDto.getIdUserResponsable() != null) {
//            UserSecurityDTO userResponsible = new UserSecurityDTO(alertActivityDto.getIdUserResponsable().longValue());
//            userResponsible.setEmail(alertActivityDto.getEmailUserResponsable());
//            notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(Arrays.asList(userCreator, userResponsible)));
//        }
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(userCreator));
//        this.notificationSenderService.sendAlertNotification(notificationDto);
//    }
//
//    @Async
//    @Override
//    public void sendNotificationErrorActivity(ErrorActivityDto errorActivityDto) {
//        UserSecurityDTO userResponsible = this.userService.getOne(errorActivityDto.getUserCreationSecurityEntity().getId());
//        ActivityDto activity = errorActivityDto.getActivityEntity();
//        UserSecurityDTO userCreator = activity.getUserCreationSecurityEntity();
//        String description;
//        String subject;
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("[USER_RESPONSIBLE]", userResponsible.getUserName());
//        parameters.put("[ERROR_DESCRIPTION]", errorActivityDto.getDescription());
//        parameters.put("[ERROR_LEVEL]", errorActivityDto.getLevel());
//        parameters.put("[ACTIVITY_NAME]", activity.getName());
//        NotificationDto notificationDto = new NotificationDto();
//        description = NotificationTemplate.TEMPLATE_ERROR_ACTIVITY.getDescription();
//        subject = NotificationTemplate.TEMPLATE_ERROR_ACTIVITY.getSubject();
//        description = NotificationLayerService.matchParametersToTemplate(description, parameters);
//        notificationDto.setTitle(NotificationTemplate.TEMPLATE_ERROR_ACTIVITY.getTitle());
//        notificationDto.setSubject(subject);
//        notificationDto.setDescription(description);
//        notificationDto.setNotificationEntities(NotificationLayerService.getUsersNotifications(Arrays.asList(userCreator,
//                userResponsible)));
//        this.notificationSenderService.sendErrorNotification(notificationDto);
//    }
//
//    @Override
//    @Transactional
//    public GroupNotificationLightDto getNotificationDontReceivedByUser(Long idUser) throws AcquaBoardException {
//        if (idUser == null || idUser < 0)
//            throw new AcquaBoardException("El usuario no es valido.");
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.addCriteriaToQuery(NotificationConstants.QUERY_NOTIFICATION_BY_USER_QUERY);
//        queryWrapper.addCriteriaToFrom(NotificationConstants.QUERY_NOTIFICATION_BY_USER_FROM);
//        queryWrapper.addCriteriaToFilter(NotificationConstants.QUERY_NOTIFICATION_BY_USER_WHERE);
//        queryWrapper.addParameter(NotificationConstants.PARAMETER_ID_USER, idUser);
//        queryWrapper.addCriteriaToOrder(NotificationConstants.QUERY_NOTIFICATION_BY_USER_ORDER);
//        List<NotificationLightDto> listResult = (List<NotificationLightDto>) queryService.getQueryResultList(queryWrapper.getQueryComplete(), queryWrapper.getParameters(), NotificationLightDto.class);
//        GroupNotificationLightDto result = new GroupNotificationLightDto();
//        result.setNotifications(listResult);
//        result.setTotal(listResult.size());
//        return result;
//    }
//
//    @Override
//    @Transactional
//    public List<NotificationLightDto> getNotificationDontNotifiedByUser(Long idUser) throws AcquaBoardException {
//        if (idUser == null || idUser < 0)
//            throw new AcquaBoardException("El usuario no es valido.");
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.addCriteriaToQuery(NotificationConstants.QUERY_NOTIFICATION_NOTIFIED_BY_USER_QUERY);
//        queryWrapper.addCriteriaToFrom(NotificationConstants.QUERY_NOTIFICATION_NOTIFIED_BY_USER_FROM);
//        queryWrapper.addCriteriaToFilter(NotificationConstants.QUERY_NOTIFICATION_NOTIFIED_BY_USER_WHERE);
//        queryWrapper.addParameter(NotificationConstants.PARAMETER_ID_USER, idUser);
//        List<NotificationLightDto> listResult = (List<NotificationLightDto>) queryService.getQueryResultList(queryWrapper.getQueryComplete(), queryWrapper.getParameters(), NotificationLightDto.class);
//        this.setNotificationsNotified(listResult);
//        return listResult;
//    }
//
//    @Override
//    @Transactional
//    public String setListNotificationsReceived(List<NotificationLightDto> listNotifications) throws AcquaBoardException {
//        if (listNotifications == null)
//            throw new AcquaBoardException("La lista de notificaciones no puede estar vacia.");
//        this.setNotificationsReceived(listNotifications);
//        return "La lista de notificaciones ha sido actualizada correctamente.";
//    }
//
//    public void setNotificationsReceived(List<NotificationLightDto> list) throws AcquaBoardException {
//        for (NotificationLightDto temp : list) {
//            UserNotificationDto userNotificationDto = this.userNotificationService.getById(temp.getId().longValue());
//            if (userNotificationDto != null) {
//                userNotificationDto.setReceived(true);
//                this.userNotificationService.save(userNotificationDto);
//            }
//        }
//    }
//
//    public void setNotificationsNotified(List<NotificationLightDto> list) throws AcquaBoardException {
//        for (NotificationLightDto temp : list) {
//            UserNotificationDto userNotificationDto = this.userNotificationService.getById(temp.getId().longValue());
//            if (userNotificationDto != null) {
//                userNotificationDto.setNotified(true);
//                this.userNotificationService.save(userNotificationDto);
//            }
//        }
//    }
//
}
