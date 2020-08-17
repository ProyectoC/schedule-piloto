package com.schedulepiloto.core.entities.model;

import com.schedulepiloto.core.entities.BaseEntity;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@Table(name = "error_audit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        ),
        @TypeDef(
                name = "int-array",
                typeClass = IntArrayType.class
        )
})
public class ErrorAuditEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "error_audit_sequence_key_id")
    @SequenceGenerator(
            name = "error_audit_sequence_key_id",
            sequenceName = "error_audit_sequence_key_id",
            initialValue = 1
    )
    private Long id;

    @Column(nullable = false, name = "code")
    private String code;

    @Column(nullable = false, name = "message")
    private String message;

    @Type(type = "string-array")
    @Column(name = "details", columnDefinition = "text[]")
    private String[] details;

    @Column(nullable = false, name = "http_session_id")
    private String httpSessionId;

    @Column(nullable = false, name = "uri")
    private String uri;

    @Column(nullable = false, name = "http_status")
    private String httpStatus;

}
