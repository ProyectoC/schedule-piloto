package com.schedulepiloto.core.entities.model;

import com.schedulepiloto.core.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "notification_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTypeEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "notification_type_sequence_key_id")
    @SequenceGenerator(
            name = "notification_type_sequence_key_id",
            sequenceName = "notification_type_sequence_key_id",
            initialValue = 1
    )
    private Long id;

    @Column(nullable = false, name = "name", unique = true)
    private String name;
}
