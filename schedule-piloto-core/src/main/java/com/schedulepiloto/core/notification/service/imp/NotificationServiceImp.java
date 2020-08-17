package com.schedulepiloto.core.notification.service.imp;

import com.schedulepiloto.core.config.NotificationConfig;
import com.schedulepiloto.core.dto.model.NotificationDto;
import com.schedulepiloto.core.email.constants.EmailConstants;
import com.schedulepiloto.core.email.model.EmailDto;
import com.schedulepiloto.core.email.model.EmailProperties;
import com.schedulepiloto.core.email.service.EmailService;
import com.schedulepiloto.core.entities.model.NotificationEntity;
import com.schedulepiloto.core.entities.model.UserNotificationEntity;
import com.schedulepiloto.core.exception.ExceptionCode;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import com.schedulepiloto.core.notification.service.NotificationService;
import com.schedulepiloto.core.repository.NotificationRepository;
import com.schedulepiloto.core.service.NotificationLayerService;
import com.schedulepiloto.core.util.CommonUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotificationServiceImp implements NotificationService {

    public static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImp.class);
    public static final String FOOTER_NOTIFICATION_MESSAGE = "\n\nGracias,\nThe Myventory Team\n";

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private NotificationConfig notificationConfig;

    @Override
    @Transactional
    public NotificationDto save(NotificationDto notificationDto) throws SchedulePilotoException {
        NotificationEntity entity = NotificationService.convertDTOToEntity(notificationDto);

        List<UserNotificationEntity> listUserNotification = entity.getNotificationEntities();
        entity.setNotificationEntities(new ArrayList<>());
        for (UserNotificationEntity temp : listUserNotification) {
            entity.addUserNotification(temp);
        }
        return NotificationService.convertEntityToDTO(repository.save(entity));
    }

    @Override
    public void initiateNotification(NotificationDto notificationDto) throws SchedulePilotoException {
        if (notificationDto.getSendType() != null && notificationDto.getSendType().equals("Validation")) {
            this.sendEmail(notificationDto.getEmails(), notificationDto.getSubject(), notificationDto.getContent());
        } else {
            Path filePath = Paths.get(this.notificationConfig.getCommon().getPathFiles(), EmailConstants.EMAIL_SEND_NOTIFICATION_USER);
            Map<String, String> parameters = new HashMap<>();
            parameters.put("[TITLE_MESSAGE]", notificationDto.getSubject());
            parameters.put("[CONTENT_MESSAGE]", notificationDto.getDescription());
            parameters.put("[SHORT_MESSAGE]", notificationDto.getDescription());
            String content = NotificationLayerService.matchParametersToFileTemplate(filePath.toString(), parameters);
            List<String> emails = NotificationService.getEmails(notificationDto);
            this.sendEmail(emails, notificationDto.getTitle(), content);
        }
    }

    public void sendEmail(List<String> emails, String subject, String content) {
        try {
            EmailDto emailDto = new EmailDto();
            emailDto.setContent(content);
            emailDto.setFrom(CommonUtil.PROJECT_NAME);
            emailDto.setSendToList(emails);
            emailDto.setSubject(subject);
            emailDto.setReplyToList(null);
            emailDto.setAttachmentsList(null);
            emailService.sendEmail(getEmailProperties(), emailDto);
        } catch (Exception e) {
            LOGGER.error(EmailConstants.ERROR_EMAIL_SEND_CLIENT, ExceptionCode.ERROR_SEND_EMAIL.getCode(), ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(EmailConstants.ERROR_EMAIL_SEND_CLIENT, e);
        }
    }

    private EmailProperties getEmailProperties() {
        EmailProperties emailProperties = new EmailProperties();
        emailProperties.setEmailOrigin(this.notificationConfig.getCommon().getOrigin());
        emailProperties.setPasswordOrigin(this.notificationConfig.getCommon().getPasswordOrigin());
        emailProperties.setSmtpAuth(this.notificationConfig.getCommon().getIsSmtpAuth());
        emailProperties.setStarttlsEnable(this.notificationConfig.getCommon().getIsStarttlsEnable());
        emailProperties.setSmtpHost(this.notificationConfig.getCommon().getSmtpHost());
        emailProperties.setSmtpPort(this.notificationConfig.getCommon().getSmtpPort());
        emailProperties.setHtmlContent(true);
        emailProperties.setAttachments(false);
        return emailProperties;
    }
}
