package com.example.AutoExpress.controllers;

import com.example.AutoExpress.entities.Discussion;
import com.example.AutoExpress.services.AuthService;
import com.example.AutoExpress.services.DiscussionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final AuthService authService;

    private final DiscussionService discussionService;

    public HomeController(AuthService authService, DiscussionService discussionService) {
        this.authService = authService;
        this.discussionService = discussionService;
    }

    @GetMapping("/")
    public String viewHome(Model model) {

        List<Discussion> discussions = discussionService.getAllDiscussions();

        //model.addAttribute("authService", authService);
        model.addAttribute("discussions", discussions);


        return "index";
    }

    @GetMapping("/about")
    public String viewAbout() {

        return "about";
    }


}
