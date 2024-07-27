
package com.example.AutoExpress.controllers;

import com.example.AutoExpress.dto.UserLoginDTO;
import com.example.AutoExpress.dto.UserRegisterDTO;
import com.example.AutoExpress.security.AppUserDetailsService;
import com.example.AutoExpress.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
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

//    @PostMapping("/login")
//    public String doLogin(@Valid UserLoginDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                                                new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
//
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//        //UsernamePasswordAuthenticationFilter
//
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("loginData", data);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);
//
//            return "redirect:/login";
//        }
//
//        boolean successfulLogin = userService.login(data);
//
//        if (!successfulLogin) {
//            redirectAttributes.addFlashAttribute("loginData", data);
//            redirectAttributes.addFlashAttribute("userPassMismatch", true);
//
//            return "redirect:/login";
//        }
//
//        return "redirect:/index";
//    }



}




