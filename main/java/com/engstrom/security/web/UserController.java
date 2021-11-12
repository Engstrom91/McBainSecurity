package com.engstrom.security.web;

import com.engstrom.security.service.UserService;
import com.engstrom.security.web.dto.UserRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistration emptyRegistration() {
        return new UserRegistration();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") UserRegistration registration) {
        userService.save(registration);
        return "redirect:/registration?success";
    }
}
