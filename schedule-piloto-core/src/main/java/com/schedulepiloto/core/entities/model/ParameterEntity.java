package com.schedulepiloto.core.entities.model;

import com.schedulepiloto.core.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "parameter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name", unique = true)
    private String name;
    @Column(nullable = false, name = "description")
    private String description;
    @Column(nullable = false, name = "encrypted")
    private Boolean encrypted;
    @Column(nullable = false, name = "value", length = 4000)
    private String value;

}
