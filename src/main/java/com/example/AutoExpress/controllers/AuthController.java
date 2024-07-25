package com.example.AutoExpress.controllers;

import com.example.AutoExpress.dto.UserRegisterDTO;
import com.example.AutoExpress.repositories.RoleRepository;
import com.example.AutoExpress.repositories.UserRepository;
import com.example.AutoExpress.security.CustomUserDetailsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    private String viewRegister() {
        return "register";
    }

    @PostMapping("/register")
    private ResponseEntity<String> doRegister(@RequestBody UserRegisterDTO registerDTO) {

        UserDetails isRegistered = userDetailsService.loadUserByUsername(registerDTO.getUsername());

        return new ResponseEntity<>( "aszi", HttpStatus.OK);
    }

}
