package com.schedulepiloto.core.audit;

import com.schedulepiloto.core.dto.model.UserAccountDto;
import com.schedulepiloto.core.util.SecurityUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated())
            return Optional.of(SecurityUtil.OAUTH_EXTERNAL_USER);

        UserAccountDto userInternal = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            try {
                userInternal = (UserAccountDto) authentication.getPrincipal();
            } catch (Exception ex) {
                return Optional.of(SecurityUtil.OAUTH_EXTERNAL_USER);
            }
            return Optional.of(userInternal == null || userInternal.getUsername() == null ? SecurityUtil.OAUTH_EXTERNAL_USER : userInternal.getUsername());
        }
        return Optional.of(SecurityUtil.OAUTH_EXTERNAL_USER);
    }
}
