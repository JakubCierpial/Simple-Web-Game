package com.example.cierpial.jakub.guild.controllers;

import com.example.cierpial.jakub.guild.dto.MessageDto;
import com.example.cierpial.jakub.guild.dto.RegisterDto;
import com.example.cierpial.jakub.guild.models.User;
import com.example.cierpial.jakub.guild.models.VerificationToken;
import com.example.cierpial.jakub.guild.services.EmailService;
import com.example.cierpial.jakub.guild.services.UserService;
import com.example.cierpial.jakub.guild.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @GetMapping("/")
    public String getMainPage()
    {
        return "index";
    }

    @GetMapping("/register")
    public String registerNewUser(Model model)
    {
        model.addAttribute("registerDto",new RegisterDto());
        return "register";
    }
    @PostMapping("/register/save")
    public String saveNewUser(Model model,@Valid  @ModelAttribute("registerDto") RegisterDto registerDto, BindingResult bindingResult, HttpServletRequest httpServletRequest)
    {
        if (bindingResult.hasErrors())
        {
            return "register";
        }
        if(userService.isExist(registerDto.getUser()))
        {
            bindingResult.addError(new FieldError("user.username","user.username"
                    ,"Podany użytkownik już istnieje"));
            return "register";
        }

        User newUser = userService.createNewUser(registerDto.getUser());
        VerificationToken token = verificationTokenService.createToken(newUser);
        emailService.sendEmail(token, newUser, httpServletRequest);
        model.addAttribute("messageDto", new MessageDto(new ArrayList<>(Arrays.asList("Na twój mail został wysłany link aktywacyjny," +
                "proszę aktywować konto przed zalogowaniem"))));
        return "message";
    }
    @GetMapping(value = "/confirm-account")
    public String confirmUserAccount(Model model, @RequestParam("token") String confirmationToken) {
        VerificationToken token = verificationTokenService.findByTokenContent(confirmationToken);

        if (token != null) {
            Optional<User> byMail = userService.findUserByMail(token.getUser().getEmail());
            if (byMail.isPresent()) {
                User user = byMail.get();
                user.setActive(true);
                userService.updateUser(user);
                model.addAttribute("messageDto", new MessageDto(new ArrayList<>(Arrays.asList("Konto zostało aktywowane"))));
            }
            else {
                model.addAttribute("messageDto", new MessageDto(new ArrayList<>(Arrays.asList("Błąd w aktywowaniu konta"))));
            }
        }
        else{
            model.addAttribute("messageDto",  new MessageDto(new ArrayList<>(Arrays.asList("Błąd w aktywowaniu konta"))));
        }

        return "message";
    }

}
