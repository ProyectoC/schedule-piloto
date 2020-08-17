package com.schedulepiloto.core.service.imp;

import com.schedulepiloto.core.dto.model.RolAccountDto;
import com.schedulepiloto.core.dto.model.TokenDto;
import com.schedulepiloto.core.dto.model.UserAccountDto;
import com.schedulepiloto.core.email.constants.EmailConstants;
import com.schedulepiloto.core.exception.SchedulePilotoException;
import com.schedulepiloto.core.request.UserAccountAuthRequest;
import com.schedulepiloto.core.request.UserAccountCreateRequest;
import com.schedulepiloto.core.response.UserAccountAuthResponse;
import com.schedulepiloto.core.security.token.service.ManageTokenService;
import com.schedulepiloto.core.service.GlobalListDinamicService;
import com.schedulepiloto.core.service.ManageUserService;
import com.schedulepiloto.core.service.NotificationLayerService;
import com.schedulepiloto.core.service.UserAccountService;
import com.schedulepiloto.core.util.dto.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
public class ManageUserServiceImp implements ManageUserService {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ManageTokenService manageTokenService;

    @Autowired
    private NotificationLayerService notificationLayerService;

    @Autowired
    private GlobalListDinamicService globalListDinamicService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public UserAccountDto createUserAccount(UserAccountCreateRequest userAccountCreateRequest) throws SchedulePilotoException {
        UserAccountDto userAccountDto = UserAccountService.convertRequestToDTO(userAccountCreateRequest);
        Validator validator = this.userAccountService.validationUserBeforeSave(userAccountDto);
        if (!validator.isValid())
            throw new SchedulePilotoException(validator.getFirstError());

        RolAccountDto rolSelected = this.globalListDinamicService.getRolAccountByIdThrow(userAccountDto.getRolAccountEntity().getId());
        validator = rolSelected.validationForCreateUser(userAccountDto);
        if (!validator.isValid())
            throw new SchedulePilotoException(validator.getFirstError());

        userAccountDto.setActivationTokenEntity(this.manageTokenService.createUserAccountActivationToken(1));
        userAccountDto.setPassword(this.passwordEncoder.encode(userAccountDto.getPassword()));
        userAccountDto.setFailedAttempts(0);
        userAccountDto.setPasswordExpiredDate(LocalDateTime.now().plusMonths(6));
        userAccountDto.setBlock(false);
        userAccountDto = this.userAccountService.save(userAccountDto);
        this.notificationLayerService.sendNotificationCreateUserAccount(userAccountDto);
        return userAccountDto;
    }

    @Override
    @Transactional
    public String activateUserAccount(String token, Long userAccountId) throws SchedulePilotoException {
        String urlConfirmEmail = this.globalListDinamicService.getParameterValueEmpty(EmailConstants.PARAMETER_URL_CONFIRM_EMAIL);
        StringBuilder uriBuilder = new StringBuilder(urlConfirmEmail);
        uriBuilder.append("?message=");

        UserAccountDto userAccountDto = this.userAccountService.getByIdThrow(userAccountId);
        Validator validator = userAccountDto.validationForActivationUser();
        if (!validator.isValid()) {
            uriBuilder.append(validator.getFirstError());
            return uriBuilder.toString();
        }

        TokenDto userToken = userAccountDto.getActivationTokenEntity();
        validator = this.manageTokenService.validateActivationUserAccountToken(userToken.getId(), token);
        if (!validator.isValid()) {
            uriBuilder.append(validator.getFirstError());
            return uriBuilder.toString();
        }

        userAccountDto.setIsActive(true);
        userAccountDto = this.userAccountService.update(userAccountDto);
        this.notificationLayerService.sendNotificationActivationUserAccount(userAccountDto);
        return uriBuilder.append(EmailConstants.SUBJECT_DEFAULT_SEND_ACTIVATE_USER_ACCOUNT).toString();
    }

    @Override
    @Transactional
    public UserAccountAuthResponse authUserAccount(UserAccountAuthRequest userAccountAuthRequest) throws SchedulePilotoException {
        UserAccountDto userAccountDto = this.userAccountService.getByUsernameThrow(userAccountAuthRequest.getUsername());
        Validator validator = userAccountDto.validationForAuthUser();
        if (!validator.isValid()) {
            throw new SchedulePilotoException(validator.getFirstError());
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userAccountAuthRequest.getUsername(),
                            userAccountAuthRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            if (ex instanceof AuthenticationException) {
                this.updateUserFailedAuth(userAccountDto);
            }
            throw new SchedulePilotoException("Auth for user account failed, username or password not valid.");
        }

        TokenDto authToken = this.manageTokenService.createUserAccountAuthToken(userAccountDto.getUsername(),
                new String[]{userAccountDto.getRolAccountEntity().getName()});
        userAccountDto.setAuthTokenEntity(authToken);
        userAccountDto.setFailedAttempts(0);
        this.userAccountService.update(userAccountDto);
        return new UserAccountAuthResponse(authToken.getKey());
    }

    private void updateUserFailedAuth(UserAccountDto userAccountDto) throws SchedulePilotoException {
        String limitLoginStr = this.globalListDinamicService.getParameterValueThrow(EmailConstants.PARAMETER_LIMIT_FAILED_ATTEMPTS);
        int limitLogin = Integer.parseInt(limitLoginStr);
        Integer failedAttempts = userAccountDto.getFailedAttempts();
        failedAttempts++;
        if (failedAttempts >= limitLogin)
            userAccountDto.setBlock(true);
        userAccountDto.setFailedAttempts(failedAttempts);
        this.userAccountService.update(userAccountDto);
    }

}
