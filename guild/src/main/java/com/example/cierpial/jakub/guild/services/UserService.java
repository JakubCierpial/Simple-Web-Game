package com.example.cierpial.jakub.guild.services;

import com.example.cierpial.jakub.guild.models.User;
import com.example.cierpial.jakub.guild.repositories.RoleRepository;
import com.example.cierpial.jakub.guild.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public boolean isExist(User user)
    {
        return userRepository.existsByUsername(user.getUsername());
    }
    public Optional<User> findUserByMail(String mail)
    {
        return userRepository.findByEmail(mail);
    }
    public User updateUser(User user)
    {
        return userRepository.save(user);
    }

}
