package com.example.cierpial.jakub.guild.util;

import com.example.cierpial.jakub.guild.models.Role;
import com.example.cierpial.jakub.guild.models.User;
import com.example.cierpial.jakub.guild.services.RoleService;
import com.example.cierpial.jakub.guild.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InsertsToDB {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @PostConstruct
    public void init()
    {
        Role userRole = roleService.createNewRole("USER");
        userService.createNewUser(new User("kebab","12345",true,"kebab@o2.pl",userRole));
    }

}
