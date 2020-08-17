package com.schedulepiloto.core.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.schedulepiloto.core.dto.BaseDto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorAuditDto extends BaseDto implements Serializable {

    private Long id;
    private String code;
    private String message;
    private String[] details;
    private String httpSessionId;
    private String uri;
    private String httpStatus;
}
