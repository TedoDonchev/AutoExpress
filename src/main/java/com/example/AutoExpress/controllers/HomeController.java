package com.example.AutoExpress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String viewHome() {
        return "index";
    }

    public void nasd() {

    }

    @GetMapping("/add")
    public String viewAdd() {
        return "add";
    }


}
