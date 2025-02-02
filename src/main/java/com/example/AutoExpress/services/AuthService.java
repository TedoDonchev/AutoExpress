package com.example.AutoExpress.services;

import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUser(){
        return userRepository
                .findByUsername(getUserDetails().getUsername())
                .orElse(null);
    }

    public UserDetails getUserDetails() {
        return (UserDetails)  getAuthentication().getPrincipal();
    }

    public boolean hasRole(String role) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }

    public boolean isAdmin() {
        return hasRole("ADMIN");
    }

    public boolean isAuthenticated() {
        return hasRole("ADMIN") || hasRole("MEMBER");
    }
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    public String getUsername() {
        return getUser().getUsername();
    }

}
