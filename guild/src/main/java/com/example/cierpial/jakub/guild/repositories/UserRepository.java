package com.example.cierpial.jakub.guild.repositories;

import com.example.cierpial.jakub.guild.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
