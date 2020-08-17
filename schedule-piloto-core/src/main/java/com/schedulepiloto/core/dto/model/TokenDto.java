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

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenDto extends BaseDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String key;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean used;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer timesUsed;
    private LocalDateTime expirationDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TokenTypeDto tokenTypeEntity;


    // Validations
    public Validator validateForActivationUser(String tokenContent) {
        Validator validator = new Validator();
        if (!this.getIsActive()) {
            validator.setValid(false);
            validator.addError("El token de seguridad no esta activo.");
            return validator;
        }
        if (this.getUsed()) {
            validator.setValid(false);
            validator.addError("El token de seguridad ya ha sido usado.");
            return validator;
        }
        if (this.getExpirationDate().isBefore(LocalDateTime.now())) {
            validator.setValid(false);
            validator.addError("El token de seguridad ya ha expirado.");
            return validator;
        }
        if (!this.key.equals(tokenContent)) {
            validator.setValid(false);
            validator.addError("El token de seguridad ya ha expirado.");
            return validator;
        }
        validator.setValid(true);
        return validator;
    }
}
