package com.example.cierpial.jakub.guild.repositories;

import com.example.cierpial.jakub.guild.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);
}
