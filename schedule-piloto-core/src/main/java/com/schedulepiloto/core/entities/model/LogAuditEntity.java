package com.schedulepiloto.core.entities.model;

import com.schedulepiloto.core.entities.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "log_audit")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogAuditEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "log_audit_sequence_key_id")
    @SequenceGenerator(
            name = "log_audit_sequence_key_id",
            sequenceName = "log_audit_sequence_key_id",
            initialValue = 1
    )
    private Long id;

    @Column(nullable = false, name = "origin")
    private String origin;

    @Column(nullable = false, name = "location")
    private String location;

    @Column(nullable = false, name = "http_method")
    private String httpMethod;

    @Column(nullable = false, name = "context_path")
    private String contextPath;

    @Column(nullable = false, name = "servlet_path")
    private String servletPath;

    @Column(nullable = false, name = "uri")
    private String uri;

    @Column(nullable = false, name = "url")
    private String url;

    @Column(nullable = false, name = "http_status")
    private String httpStatus;

    @Column(nullable = false, name = "client_ip")
    private String clientIp;

    @Column(nullable = false, name = "client_host")
    private String clientHost;

    @Column(nullable = false, name = "client_port")
    private String clientPort;

    @Column(nullable = false, name = "scheme")
    private String scheme;

    @Column(nullable = false, name = "http_session_id")
    private String httpSessionId;

    @Column(nullable = false, name = "request_session_id")
    private String requestSessionId;

    @Column(nullable = true, name = "request_user_auth")
    private String requestUserAuth;

    @Column(nullable = false, name = "content_type_request")
    private String contentTypeRequest;

    @Column(nullable = false, name = "character_encoding")
    private String characterEncoding;
}
