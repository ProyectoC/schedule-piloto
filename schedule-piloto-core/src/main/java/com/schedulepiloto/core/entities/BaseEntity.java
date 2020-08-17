package com.schedulepiloto.core.entities;

import com.schedulepiloto.core.audit.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity extends Auditable<String> {

    // By default is true.
    @Column(nullable = true, name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;
}
