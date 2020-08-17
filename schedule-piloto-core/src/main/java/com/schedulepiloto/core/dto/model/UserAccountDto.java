package com.schedulepiloto.core.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.schedulepiloto.core.dto.BaseDto;
import com.schedulepiloto.core.util.dto.Validator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAccountDto extends BaseDto implements UserDetails {

    private Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer failedAttempts;
    private LocalDateTime passwordExpiredDate;
    private String firstName;
    private String lastName;
    private String identification;
    private String identificationCode;
    private String email;
    private String emailBackup;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean block = false;
    private RolAccountDto rolAccountEntity;
    private CollegeCareerDto collegeCareerEntity;
    private TokenDto authTokenEntity;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TokenDto activationTokenEntity;
    private List<UserNotificationDto> notificationEntities;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserAccountDto create(UserAccountDto user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRolAccountEntity().getName()));
        user.setAuthorities(authorities);
        user.setPassword(user.getPassword());
        return user;
    }

    // Validations
    public Validator validationForActivationUser() {
        Validator validator = new Validator();
        if (this.getBlock()) {
            validator.setValid(false);
            validator.addError("The activation of the user account failed, user is blocked.");
            return validator;
        }
        if (this.getIsActive()) {
            validator.setValid(false);
            validator.addError("Activation of user account failed, user account is already active.");
            return validator;
        }
        validator.setValid(true);
        return validator;
    }

    public Validator validationForAuthUser() {
        Validator validator = new Validator();
        if (this.getBlock()) {
            validator.setValid(false);
            validator.addError("Auth for user account failed, user is blocked.");
            return validator;
        }
        if (!this.getIsActive()) {
            validator.setValid(false);
            validator.addError("Auth for user account failed, user account is not active yet.");
            return validator;
        }
        if (!this.getActivationTokenEntity().getUsed()) {
            validator.setValid(false);
            validator.addError("Auth for user account failed, user account is not active yet.");
            return validator;
        }
        if (this.getPasswordExpiredDate().isBefore(LocalDateTime.now())) {
            validator.setValid(false);
            validator.addError("Auth for user account failed, user account password has expired.");
            return validator;
        }
        validator.setValid(true);
        return validator;
    }

}
