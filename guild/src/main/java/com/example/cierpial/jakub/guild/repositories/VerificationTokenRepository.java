package com.example.cierpial.jakub.guild.repositories;

import com.example.cierpial.jakub.guild.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    VerificationToken findByToken(String Token);
}
