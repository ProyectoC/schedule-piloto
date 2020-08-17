package com.schedulepiloto.core.service;

import com.schedulepiloto.core.dto.model.UserAccountDto;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import com.schedulepiloto.core.request.UserAccountAuthRequest;
import com.schedulepiloto.core.request.UserAccountCreateRequest;
import com.schedulepiloto.core.response.UserAccountAuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public interface ManageUserService {

    Logger LOGGER = LoggerFactory.getLogger(ManageUserService.class);

    UserAccountDto createUserAccount(UserAccountCreateRequest userAccountCreateRequest) throws SchedulePilotoException;

    String activateUserAccount(String token, Long userAccountId) throws SchedulePilotoException;

    UserAccountAuthResponse authUserAccount(UserAccountAuthRequest userAccountAuthRequest) throws SchedulePilotoException;

}
