package com.example.AutoExpress.controllers;


import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.services.AdminService;
import com.example.AutoExpress.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @PutMapping("/users/disable/{id}")
    public String disableUser(@PathVariable("id") long id) {
        UserEntity user = userService.getById(id);
        adminService.disableUser(user);

        return "redirect:/users/" + user.getUsername();
    }

    @PutMapping("/users/enable/{id}")
    public String enableUser(@PathVariable("id") long id) {
        UserEntity user = userService.getById(id);
        adminService.enableUser(user);

        return "redirect:/users/" + user.getUsername();
    }

}
