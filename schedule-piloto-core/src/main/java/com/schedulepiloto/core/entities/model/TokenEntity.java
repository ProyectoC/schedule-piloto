package com.schedulepiloto.core.entities.model;

import com.schedulepiloto.core.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "token_sequence_key_id")
    @SequenceGenerator(
            name = "token_sequence_key_id",
            sequenceName = "token_sequence_key_id",
            initialValue = 1
    )
    private Long id;

    @Column(nullable = false, name = "key", length = 4000)
    private String key;

    @Column(nullable = false, name = "used", columnDefinition = "boolean default true")
    private Boolean used = false;

    @Column(nullable = false, name = "times_used")
    private Integer timesUsed = 0;

    @Column(nullable = true, name = "expiration_date")
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "token_type_id_fk", nullable = false)
    private TokenTypeEntity tokenTypeEntity;

}
