package com.schedulepiloto.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app.configuration.oauth")
public class OAuthConfig {

    private final SocialNetworks socialNetworks = new SocialNetworks();

    @Data
    public static class SocialNetworks {

        private String tokenSecret;
        private long tokenExpirationMsec;
        private List<String> authorizedRedirectUris = new ArrayList<>();
    }
}
