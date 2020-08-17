package com.schedulepiloto.core.entities.model;

import com.schedulepiloto.core.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "notification_sequence_key_id")
    @SequenceGenerator(
            name = "notification_sequence_key_id",
            sequenceName = "notification_sequence_key_id",
            initialValue = 1
    )
    private Long id;

    @Column(nullable = false, name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_notification_type_fk", nullable = false)
    private NotificationTypeEntity notificationTypeEntity;

    @OneToMany(mappedBy = "notificationEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserNotificationEntity> notificationEntities;

    public NotificationEntity(Long id, String description, NotificationTypeEntity notificationTypeEntity) {
        this.id = id;
        this.description = description;
        this.notificationTypeEntity = notificationTypeEntity;
    }

    public void addUserNotification(UserNotificationEntity userNotificationEntity) {
        userNotificationEntity.setReceived(false);
        userNotificationEntity.setNotified(false);
        this.notificationEntities.add(userNotificationEntity);
        userNotificationEntity.setNotificationEntity(this);
    }
}
