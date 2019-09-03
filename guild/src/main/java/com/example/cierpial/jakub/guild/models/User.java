package com.example.cierpial.jakub.guild.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {

    public User(String username, String password, boolean active, String email, Role role) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.email = email;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;
    private boolean active;
    private String email;

    @ManyToOne
    private Role role;



}
