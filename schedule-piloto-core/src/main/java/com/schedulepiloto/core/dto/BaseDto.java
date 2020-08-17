package com.schedulepiloto.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean isActive = true;

    // Auditable
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String createdBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date createdDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastModifiedBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date lastModifiedDate;
}
