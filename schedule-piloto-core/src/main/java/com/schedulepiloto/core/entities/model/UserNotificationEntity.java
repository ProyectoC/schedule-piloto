package com.schedulepiloto.core.entities.model;

import com.schedulepiloto.core.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "user_notification_sequence_key_id")
    @SequenceGenerator(
            name = "user_notification_sequence_key_id",
            sequenceName = "user_notification_sequence_key_id",
            initialValue = 1
    )
    private Long id;

    @Column(nullable = false, name = "notified")
    private Boolean notified;

    @Column(nullable = false, name = "received")
    private Boolean received;

    @ManyToOne
    @JoinColumn(name = "user_account_id_fk", nullable = false)
    private UserAccountEntity userAccountEntity;

    @ManyToOne
    @JoinColumn(name = "notification_id_fk", nullable = false)
    private NotificationEntity notificationEntity;

}
