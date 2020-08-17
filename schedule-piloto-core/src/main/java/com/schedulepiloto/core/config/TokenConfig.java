package com.schedulepiloto.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Data
@ConfigurationProperties(prefix = "app.configuration.token")
public class TokenConfig {

    private final Clients clients = new Clients();

    @Data
    public static class Clients {

        private final Host host = new Host();
        private final UserCommon userCommon = new UserCommon();

        @Data
        public static class Host {
            @NotNull(message = "Key Host Token can not be null")
            private String key;
            @NotNull(message = "Expiration Time Host Token can not be null")
            private Long expirationTime;
        }

        @Data
        public static class UserCommon {
            @NotNull(message = "Key User Common Token can not be null")
            private String key;
            @NotNull(message = "Expiration Time User Common Token can not be null")
            private Integer expirationTime;
            @NotNull(message = "Key Validate Register User Common Token can not be null")
            private String keyValidateRegister;
        }
    }
}
