package ru.javamentor.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthCntroller {

    @GetMapping ("/login")
    public String loginPage() {
        return "pages/auth/login";
    }
}

