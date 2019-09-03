package com.example.cierpial.jakub.guild.services;

import com.example.cierpial.jakub.guild.models.User;
import com.example.cierpial.jakub.guild.repositories.RoleRepository;
import com.example.cierpial.jakub.guild.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createNewUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByRole("USER"));
        return userRepository.save(user);
    }

}
