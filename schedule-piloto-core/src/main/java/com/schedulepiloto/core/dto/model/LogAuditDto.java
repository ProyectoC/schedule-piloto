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
public class LogAuditDto extends BaseDto implements Serializable {

    private Long id;
    private String origin;
    private String location;
    private String httpMethod;
    private String contextPath;
    private String servletPath;
    private String uri;
    private String url;
    private String httpStatus;
    private String clientIp;
    private String clientHost;
    private String clientPort;
    private String scheme;
    private String httpSessionId;
    private String requestSessionId;
    private String requestUserAuth;
    private String contentTypeRequest;
    private String characterEncoding;
}
