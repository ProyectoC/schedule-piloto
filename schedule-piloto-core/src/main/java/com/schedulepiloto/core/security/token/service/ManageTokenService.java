package com.schedulepiloto.core.security.token.service;

import com.schedulepiloto.core.dto.model.TokenDto;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import com.schedulepiloto.core.util.dto.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public interface ManageTokenService {

    // Default Logger
    Logger LOGGER = LoggerFactory.getLogger(ManageTokenService.class);

    // Constants
    String TOKEN_PARAMETER_USERNAME = "username";
    String TOKEN_PARAMETER_GRANT_TYPE = "grant_type";
    String TOKEN_PARAMETER_ID_TYPE = "id_type";

    String TOKEN_VALUE_CLIENT_CREDENTIALS = "client_credentials";
    String TOKEN_VALUE_USER_EXTERNAL = "user_external";

    String TOKEN_TYPE_LOGIN_USER = "Login User";
    String TOKEN_TYPE_ACTIVATE_USER = "Activate User";

    // Error constants
    String ERROR_TOKEN_USER_CREATION_UNSUPPORTED = "ERROR CREATING TOKEN (User) UNSUPPORTED: {}";
    String ERROR_TOKEN_USER_CREATION = "ERROR CREATING TOKEN (User): {}";
    String ERROR_TOKEN_USER_VALIDATION_UNSUPPORTED = "ERROR VALIDATING TOKEN (User) UNSUPPORTED: {}";
    String ERROR_TOKEN_USER_VALIDATION = "ERROR VALIDATING TOKEN (User): {}";
    String ERROR_TOKEN_USER_ACTIVATION_CREATION = "ERROR CREATING TOKEN (User Activation): {}";
    String ERROR_TOKEN_TYPE_NOT_FOUND = "ERROR TOKEN TYPE NOT FOUND: {}";

    // Methods
    TokenDto createUserAccountAuthToken(String userName, String[] authorities);

    TokenDto createUserAccountActivationToken(Integer daysExpiration);

    String validateUserToken(String token, String keySecret);

    Validator validateActivationUserAccountToken(Long tokenId, String tokenContent) throws SchedulePilotoException;
}
