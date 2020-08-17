package com.schedulepiloto.core.email.model;

import com.schedulepiloto.core.email.constants.EmailConstants;

import javax.validation.constraints.NotNull;

public class EmailProperties {

    @NotNull(message = EmailConstants.NULL_EMAIL_ORIGIN_MESSAGE)
    private String emailOrigin;
    private boolean isSmtpAuth;
    private boolean isStarttlsEnable;
    @NotNull(message = EmailConstants.NULL_SMTP_HOST_MESSAGE)
    private String smtpHost;
    @NotNull(message = EmailConstants.NULL_SMTP_PORT_MESSAGE)
    private String smtpPort;
    private boolean isHtmlContent;
    private boolean isAttachments;
    @NotNull(message = EmailConstants.NULL_PASSWORD_MESSAGE)
    private String passwordOrigin;

    public EmailProperties() {

    }

    public EmailProperties(@NotNull(message = EmailConstants.NULL_EMAIL_ORIGIN_MESSAGE) String emailOrigin, boolean isSmtpAuth, boolean isStarttlsEnable, @NotNull(message = EmailConstants.NULL_SMTP_HOST_MESSAGE) String smtpHost, @NotNull(message = EmailConstants.NULL_SMTP_PORT_MESSAGE) String smtpPort, boolean isHtmlContent, boolean isAttachments, @NotNull(message = EmailConstants.NULL_PASSWORD_MESSAGE) String passwordOrigin) {
        this.emailOrigin = emailOrigin;
        this.isSmtpAuth = isSmtpAuth;
        this.isStarttlsEnable = isStarttlsEnable;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.isHtmlContent = isHtmlContent;
        this.isAttachments = isAttachments;
        this.passwordOrigin = passwordOrigin;
    }

    public boolean isSmtpAuth() {
        return isSmtpAuth;
    }

    public void setSmtpAuth(boolean smtpAuth) {
        isSmtpAuth = smtpAuth;
    }

    public boolean isStarttlsEnable() {
        return isStarttlsEnable;
    }

    public void setStarttlsEnable(boolean starttlsEnable) {
        isStarttlsEnable = starttlsEnable;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public boolean isHtmlContent() {
        return isHtmlContent;
    }

    public void setHtmlContent(boolean htmlContent) {
        isHtmlContent = htmlContent;
    }

    public boolean isAttachments() {
        return isAttachments;
    }

    public void setAttachments(boolean attachments) {
        isAttachments = attachments;
    }

    public String getEmailOrigin() {
        return emailOrigin;
    }

    public void setEmailOrigin(String emailOrigin) {
        this.emailOrigin = emailOrigin;
    }

    public String getPasswordOrigin() {
        return passwordOrigin;
    }

    public void setPasswordOrigin(String passwordOrigin) {
        this.passwordOrigin = passwordOrigin;
    }
}
