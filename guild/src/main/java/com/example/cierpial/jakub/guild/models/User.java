package com.example.cierpial.jakub.guild.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class User {

    public User(String username, String password, boolean active, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull

    @Column(unique = true)
    @Length(min = 5, max = 26, message = "Nazwa musi zawierać od 5 do 26 znaków")
    @NotNull
    @Pattern(regexp = "\\w*", message = "Błędny format nazwy, dozwolone tylko litery i cyfry")
    private String username;

    //@Length(min = 6,max = 26, message = "Hasło musi zawierać od 6 do 26 znaków")
    private String password;

    @NotNull
    private boolean active;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Błędny email")
    private String email;

    @ManyToOne
    private Role role;

    @PrePersist
    public void init()
    {
        this.active=false;
    }



}
