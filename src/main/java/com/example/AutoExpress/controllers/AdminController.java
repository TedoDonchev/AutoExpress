package com.example.AutoExpress.controllers;


import com.example.AutoExpress.dto.UserNameDTO;
import com.example.AutoExpress.dto.UserRegisterDTO;
import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.services.AdminService;
import com.example.AutoExpress.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/users/changeUsername/{id}")
    public String viewChangeUsername(@PathVariable("id") long id, Model model) {
        UserEntity user = userService.getById(id);

        model.addAttribute("user", user);

        return "change-username";
    }

    @PutMapping("/users/changeUsername/{id}")
    public String changeUserName(@PathVariable("id") long id, @Valid UserNameDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

//        if (!userService.isUsernameUnique(data.getUsername())) {
//            bindingResult.addError(new FieldError("data", "username", "User with this username already exists"));
//        }
//
//
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("usernameData", data);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usernameData", bindingResult);
//
//            return "/users/changeUsername/" + id;
//        }


        UserEntity user = adminService.changeUsername(id, data);


        return "redirect:/users/" + user.getUsername();
    }

}
