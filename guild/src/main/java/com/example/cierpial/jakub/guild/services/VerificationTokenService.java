package com.example.cierpial.jakub.guild.services;

import com.example.cierpial.jakub.guild.models.User;
import com.example.cierpial.jakub.guild.models.VerificationToken;
import com.example.cierpial.jakub.guild.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public VerificationToken createToken(User user) {
        VerificationToken verificationToken = new VerificationToken(user);
        return verificationTokenRepository.save(verificationToken);
    }

    public VerificationToken findByTokenContent(String token) {
        return verificationTokenRepository.findByToken(token);
    }
}