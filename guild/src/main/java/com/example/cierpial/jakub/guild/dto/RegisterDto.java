package com.example.cierpial.jakub.guild.dto;

import com.example.cierpial.jakub.guild.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    @Valid
    private User user;
}
