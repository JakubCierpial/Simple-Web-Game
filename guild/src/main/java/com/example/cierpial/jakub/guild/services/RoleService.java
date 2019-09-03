package com.example.cierpial.jakub.guild.services;

import com.example.cierpial.jakub.guild.models.Role;
import com.example.cierpial.jakub.guild.models.User;
import com.example.cierpial.jakub.guild.repositories.RoleRepository;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role createNewRole(String role)
    {
        return roleRepository.save(new Role(role));
    }
}
