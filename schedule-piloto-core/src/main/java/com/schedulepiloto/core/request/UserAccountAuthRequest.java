package com.schedulepiloto.core.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAccountAuthRequest {

    @NotNull(message = "username can not be null.")
    @NotEmpty(message = "username can not be empty")
    private String username;
    @NotNull(message = "password can not be null.")
    @NotEmpty(message = "password can not be empty")
    private String password;
}
