package com.example.zadanie29;

import com.example.zadanie29.user.UserInfoService;
import com.example.zadanie29.user.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogInController {

    private UserInfoService userInfoService;

    public LogInController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/login")
    String login() {
        return "login-form";
    }

    @GetMapping("/register")
    String register(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    String registerUser(UserRegistrationDto dto, Model model) {
        userInfoService.register(dto);
        model.addAttribute("confirmation", "Konto zarejestrowane pomyślnie! Zaloguj się.");
        return "redirect:/login-form";
    }
}
