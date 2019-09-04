package com.example.cierpial.jakub.guild.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

import static java.time.OffsetDateTime.now;

@Entity
@NoArgsConstructor

@Data
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private OffsetDateTime expiryDate;

    public VerificationToken(User userTable) {
        this.user = userTable;
        token = UUID.randomUUID().toString();
    }

    @PrePersist
    protected void init() {
        this.expiryDate = now();
    }

}
