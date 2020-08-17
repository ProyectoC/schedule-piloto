package com.schedulepiloto.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "app.configuration.job")
public class JobConfig {

    private final Clients clients = new Clients();

    @Data
    public static class Clients {

        private final Notification notification = new Notification();

        @Data
        public static class Notification {
            @NotNull(message = "Cron Expression Notification Job can not be null")
            private String cronExpression;
        }
    }

}
