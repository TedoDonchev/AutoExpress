
package com.example.AutoExpress.controllers;

import com.example.AutoExpress.dto.UserRegisterDTO;
import com.example.AutoExpress.exceotions.UserAlreadyExistsException;
import com.example.AutoExpress.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO createEmptyDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping("/register")
    public String viewRegister() {

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (!userService.isUsernameUnique(data.getUsername())) {
            bindingResult.addError(new FieldError("data", "username", "User with this username already exists"));
        }

        if (!userService.isEmailUnique(data.getEmail())) {
            bindingResult.addError(new FieldError("data", "email", "User with this email already exists"));
        }

        if (!data.getPassword().equals(data.getConfirmPassword())) {
            bindingResult.addError(new FieldError("data", "confirmPassword", "Passwords must match"));
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/register";
        }



        userService.register(data);

        return ("redirect:/login");
    }

//    @ModelAttribute("loginData")
//    public UserLoginDTO loginData() {
//        return new UserLoginDTO();
//    }

    @GetMapping("/login")
    public String viewLogin() {
        return "login";
    }



}




