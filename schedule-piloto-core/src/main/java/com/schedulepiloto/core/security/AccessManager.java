package com.schedulepiloto.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("accessManager")
public class AccessManager {

    public static final Logger LOGGER = LoggerFactory.getLogger(AccessManager.class);

    public boolean hasAccess(String... statusesNotAllowed) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }
}
