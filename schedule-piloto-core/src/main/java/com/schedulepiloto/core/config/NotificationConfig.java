package com.schedulepiloto.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Data
@ConfigurationProperties(prefix = "app.configuration.notification")
public class NotificationConfig {

    private final Common common = new Common();

    @Data
    public static class Common {

        @NotNull(message = "Origin Common Notification can not be null")
        private String origin;
        @NotNull(message = "Password Origin Common Notification can not be null")
        private String passwordOrigin;
        @NotNull(message = "Smtp Auth Common Notification can not be null")
        private Boolean isSmtpAuth;
        @NotNull(message = "Starttls Enable Common Notification can not be null")
        private Boolean isStarttlsEnable;
        @NotNull(message = "Smtp Host Common Notification can not be null")
        private String smtpHost;
        @NotNull(message = "Smtp Port Common Notification can not be null")
        private String smtpPort;
        @NotNull(message = "Us Common Notification can not be null")
        private String us;
        @NotNull(message = "Path Files Common Notification can not be null")
        private String pathFiles;

    }
}
