package com.schedulepiloto.core.audit;

import com.schedulepiloto.core.dto.model.LogAuditDto;
import com.schedulepiloto.core.exception.ExceptionCode;
import com.schedulepiloto.core.service.LogAuditService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuditorInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditorInterceptor.class);
    public static final String ERROR_SECURITY_LOG = "ERROR TO THE REGISTER THE LOG SECURITY, ERROR CODE: {} - ERROR DESCRIPTION: {}";

    @Autowired
    private LogAuditService logAuditService;

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object handler, Exception exception) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated())
                LOGGER.warn("### AUTH: Unknown user ... Unauthenticated. ###");

            LogAuditDto logAuditDto = LogAuditDto.builder()
                    .origin(httpServletRequest.getHeader("User-Agent") == null ? "" : httpServletRequest.getHeader("User-Agent"))
                    .location(httpServletRequest.getLocale().getCountry() == null ? "" : httpServletRequest.getLocale().getCountry())
                    .httpMethod(httpServletRequest.getMethod() == null ? "" : httpServletRequest.getMethod())
                    .contextPath(httpServletRequest.getContextPath() == null ? "" : httpServletRequest.getContextPath())
                    .servletPath(httpServletRequest.getServletPath() == null ? "" : httpServletRequest.getServletPath())
                    .uri(httpServletRequest.getRequestURI() == null ? "" : httpServletRequest.getRequestURI())
                    .url(httpServletRequest.getRequestURL() == null ? "" : httpServletRequest.getRequestURL().toString())
                    .httpStatus(httpServletResponse.getStatus() + "")
                    .clientIp(httpServletRequest.getRemoteAddr() == null ? "" : httpServletRequest.getRemoteAddr())
                    .clientHost(httpServletRequest.getRemoteHost() == null ? "" : httpServletRequest.getRemoteHost())
                    .clientPort(httpServletRequest.getRemotePort() + "")
                    .scheme(httpServletRequest.getScheme() == null ? "" : httpServletRequest.getScheme())
                    .httpSessionId("OAUTH2")
                    .requestSessionId(httpServletRequest.getRequestedSessionId() == null ? "" : httpServletRequest.getRequestedSessionId())
                    .contentTypeRequest(httpServletRequest.getContentType() == null ? "" : httpServletRequest.getContentType())
                    .characterEncoding(httpServletRequest.getCharacterEncoding() == null ? "" : httpServletRequest.getCharacterEncoding())
                    .build();
            logAuditDto.setRequestUserAuth(httpServletRequest.getUserPrincipal() == null ? "" : httpServletRequest.getUserPrincipal().getName());
            logAuditService.saveCommonLog(logAuditDto);
            LOGGER.info("### AUTH: Log security registered for: {} ###", httpServletRequest.getRequestURI());
        } catch (Exception e) {
            LOGGER.error(ERROR_SECURITY_LOG, ExceptionCode.ERROR_REGISTER_LOG.getCode(), ExceptionUtils.getStackTrace(e));
        }
    }
}
