package com.example.cierpial.jakub.guild.repositories;

import com.example.cierpial.jakub.guild.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRole(String role);

}
