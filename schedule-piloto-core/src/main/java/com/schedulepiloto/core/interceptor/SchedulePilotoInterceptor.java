package com.schedulepiloto.core.interceptor;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class SchedulePilotoInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulePilotoInterceptor.class);
    private static final String[] LOCAL_HOST_ADDRESS_LIST = new String[]{"0:0:0:0:0:0:0:1", "127.0.0.1"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            List<String> list = Arrays.asList(LOCAL_HOST_ADDRESS_LIST);
            if (list.contains(request.getRemoteAddr()))
                return true;
            LOGGER.info("*********************************** START ***************************************");
            LOGGER.info("----------------------------------------------------------------");
            LOGGER.info("OK In preHandle");
            LOGGER.info("----------------------------------------------------------------");
            LOGGER.info("Service Calling: " + request.getRequestURI());
            LOGGER.info("Method HHTP: " + request.getMethod());
            LOGGER.info("Client IP Address: " + request.getRemoteAddr());
            LOGGER.info("Status: " + response.getStatus());
            LOGGER.info("OS: " + request.getHeader("User-Agent"));
            LOGGER.info("Location: " + request.getLocale().getCountry());
            LOGGER.info("----------------------------------------------------------------");
            return true;
        } catch (Exception e) {
            LOGGER.error("----------------------------------------------------------------");
            LOGGER.error("ERROR In preHandle, service calling: " + request.getRequestURI());
            LOGGER.error("----------------------------------------------------------------");
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            LOGGER.error("----------------------------------------------------------------");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        try {
            List<String> list = Arrays.asList(LOCAL_HOST_ADDRESS_LIST);
            if (list.contains(request.getRemoteAddr()))
                return;
            LOGGER.info("----------------------------------------------------------------");
            LOGGER.info("OK In postHandle");
            LOGGER.info("----------------------------------------------------------------");
            LOGGER.info("Service Calling: " + request.getRequestURI());
            LOGGER.info("Method HHTP: " + request.getMethod());
            LOGGER.info("Client IP Address: " + request.getRemoteAddr());
            LOGGER.info("Status: " + response.getStatus());
            LOGGER.info("OS: " + request.getHeader("User-Agent"));
            LOGGER.info("Location: " + request.getLocale().getCountry());
            LOGGER.info("----------------------------------------------------------------");
        } catch (Exception e) {
            LOGGER.error("----------------------------------------------------------------");
            LOGGER.error("ERROR In postHandle, service calling: " + request.getRequestURI());
            LOGGER.error("----------------------------------------------------------------");
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            LOGGER.error("----------------------------------------------------------------");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception exception) {
        try {
            List<String> list = Arrays.asList(LOCAL_HOST_ADDRESS_LIST);
            if (list.contains(request.getRemoteAddr()))
                return;
            LOGGER.info("----------------------------------------------------------------");
            LOGGER.info("OK In afterCompletion");
            LOGGER.info("----------------------------------------------------------------");
            LOGGER.info("Service Calling: " + request.getRequestURI());
            LOGGER.info("Method HHTP: " + request.getMethod());
            LOGGER.info("Client IP Address: " + request.getRemoteAddr());
            LOGGER.info("Status: " + response.getStatus());
            LOGGER.info("OS: " + request.getHeader("User-Agent"));
            LOGGER.info("Location: " + request.getLocale().getCountry());
            LOGGER.info("----------------------------------------------------------------");
        } catch (Exception e) {
            LOGGER.error("----------------------------------------------------------------");
            LOGGER.error("ERROR In afterCompletion, service calling: " + request.getRequestURI());
            LOGGER.error("----------------------------------------------------------------");
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            LOGGER.error("----------------------------------------------------------------");
        }
        LOGGER.info("*********************************** FINAL ***************************************");
    }
}
