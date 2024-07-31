package com.example.AutoExpress.controllers;

import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public String viewUser(@PathVariable("username") String username, Model model) {

        UserEntity user = userService.getUserByUserName(username);

        model.addAttribute("user", user);

        return "userProfile";

    }

}
