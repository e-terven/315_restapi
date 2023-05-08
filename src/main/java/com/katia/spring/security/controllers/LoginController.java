package com.katia.spring.security.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katia.spring.security.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class LoginController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    public LoginController (UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/api/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/api/user")
    public String showUserInfo(Principal principal, Model model) {
        model.addAttribute("activeUser", userService.findByEmail(principal.getName()));
        return "show";
    }

    @GetMapping("/api/admin")
    public String showAdminPage(Principal principal, Model model) {
        model.addAttribute("activeAdmin", userService.findByEmail(principal.getName()));
        return "users";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }
}



