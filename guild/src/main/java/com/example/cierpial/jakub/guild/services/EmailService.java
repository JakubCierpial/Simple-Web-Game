package com.example.cierpial.jakub.guild.services;


import com.example.cierpial.jakub.guild.models.User;
import com.example.cierpial.jakub.guild.models.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class EmailService {

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Async
    public void sendEmail(VerificationToken token, User user, HttpServletRequest request) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom(sender);
        String serverName = request.getServerName();
        String regex = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b";
        String port = (serverName.matches(regex) || serverName.equals("localhost")) ? ":" + request.getServerPort() : "";
        mailMessage.setText("To confirm your account, please click here : "
                + "http://" + request.getServerName() + port + "/confirm-account?token=" + token.getToken());
        javaMailSender.send(mailMessage);
    }
}