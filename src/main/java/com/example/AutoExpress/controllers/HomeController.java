package com.example.AutoExpress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String viewHome() {
        return "index";
    }

    @GetMapping("/register")
    public String viewRegister() {

        return "register";
    }

    @GetMapping("/login")
    public String viewLogin() {

        return "login";
    }


}
