package com.schedulepiloto.core.email.service.imp;

import com.schedulepiloto.core.email.constants.EmailConstants;
import com.schedulepiloto.core.email.model.EmailDto;
import com.schedulepiloto.core.email.model.EmailProperties;
import com.schedulepiloto.core.email.service.EmailService;
import com.schedulepiloto.core.exception.ExceptionCode;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import com.schedulepiloto.core.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

@Component
public class EmailServiceImp implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImp.class);
    private static final String LOG_SEND_EMAIL_OK = "EMAIL SENDER: OK ---> {}";
    private static final String LOG_SEND_EMAIL_ERROR = "EMAIL SENDER: ERROR ---> {}";
    private static final String LOG_FAILED_EMAIL_PROPERTIES = "FAILED EMAIL PROPERTIES: {}";
    private static final String LOG_FAILED_EMAIL_CONTENT = "FAILED EMAIL CONTENT: {}";

    @Async
    @Override
    @Retryable(value = {SchedulePilotoException.class}, maxAttempts = 3, backoff = @Backoff(5000))
    public void sendEmail(EmailProperties emailProperties, EmailDto emailDto) throws SchedulePilotoException {
        try {
            if (!validateEmailConfiguration(emailProperties, emailDto))
                throw new Exception(EmailConstants.ERROR_EMAIL_PROPERTIES);
            Properties props = new Properties();
            if (emailProperties.isSmtpAuth())
                props.put("mail.smtp.auth", "true");
            else
                props.put("mail.smtp.auth", "false");
            if (emailProperties.isStarttlsEnable())
                props.put("mail.smtp.starttls.enable", "true");
            else
                props.put("mail.smtp.starttls.enable", "false");
            props.put("mail.smtp.host", emailProperties.getSmtpHost());
            props.put("mail.smtp.port", emailProperties.getSmtpPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailProperties.getEmailOrigin(), emailProperties.getPasswordOrigin());
                }
            });
            Message msg = new MimeMessage(session);
            msg.setHeader(CommonUtil.HEADER_CONTENT_TYPE, CommonUtil.HEADER_VALUES_CONTENT_TYPE);
            msg.setFrom(new InternetAddress(emailProperties.getEmailOrigin(), false));

            List<Address> sendersList = new ArrayList<>();
            for (String email : emailDto.getSendToList())
                sendersList.add(new InternetAddress(email));
            Address[] sendersArray = sendersList.stream().toArray(Address[]::new);
            msg.setRecipients(Message.RecipientType.TO, sendersArray);

            if (emailDto.getReplyToList() != null) {
                List<Address> repliesList = new ArrayList<>();
                for (String email : emailDto.getReplyToList())
                    repliesList.add(new InternetAddress(email));
                Address[] repliesArray = repliesList.stream().toArray(Address[]::new);
                msg.setReplyTo(repliesArray);
            }

            msg.setSubject(emailDto.getSubject());
            if (emailProperties.isHtmlContent())
                msg.setContent(emailDto.getContent(), CommonUtil.TYPE_HTML);
            else
                msg.setContent(emailDto.getContent(), CommonUtil.TYPE_PLAIN);
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            if (emailProperties.isHtmlContent())
                messageBodyPart.setContent(emailDto.getContent(), CommonUtil.TYPE_HTML);
            else
                messageBodyPart.setContent(emailDto.getContent(), CommonUtil.TYPE_PLAIN);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            if (emailProperties.isAttachments()) {
                MimeBodyPart attachPart;
                for (String pathFile : emailDto.getAttachmentsList()) {
                    attachPart = new MimeBodyPart();
                    attachPart.attachFile(pathFile);
                    multipart.addBodyPart(attachPart);
                }
            }
            msg.setContent(multipart, CommonUtil.ENCODING_DEFAULT);
            Transport.send(msg);
            LOGGER.info(LOG_SEND_EMAIL_OK, Arrays.toString(sendersArray));
        } catch (Exception ex) {
            LOGGER.error(LOG_SEND_EMAIL_ERROR, ex.getMessage());
            throw new SchedulePilotoException(ExceptionCode.ERROR_SEND_EMAIL, ex);
        }
    }

    @Recover
    public void sendEmailRecovery(SchedulePilotoException t, EmailProperties emailProperties, EmailDto emailDto) {
        LOGGER.info(String.format("Retry Recovery - %s", t.getError().getDescription()));
        LOGGER.error("It was not possible to resend the notification to: {}", emailDto.getSendToList());
    }

    private boolean validateEmailConfiguration(EmailProperties emailProperties, EmailDto emailDto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<EmailProperties>> errorsProperties = validator.validate(emailProperties);
        for (ConstraintViolation<EmailProperties> cv : errorsProperties) {
            LOGGER.error(LOG_FAILED_EMAIL_PROPERTIES, cv.getMessage());
        }
        if (errorsProperties.size() > 0)
            return false;
        Set<ConstraintViolation<EmailDto>> errorsDto = validator.validate(emailDto);
        for (ConstraintViolation<EmailDto> cv : errorsDto) {
            LOGGER.error(LOG_FAILED_EMAIL_CONTENT, cv.getMessage());
        }
        return errorsDto.size() <= 0;
    }
}
