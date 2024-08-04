package com.example.AutoExpress.dto;

import jakarta.validation.constraints.*;


public class UserRegisterDTO {
    @NotBlank(message = "Please enter a username")
    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;

    @NotBlank(message = "Please enter an email")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Please enter a password")
    @Size(min = 3, message = "Password must be at least 3 characters long")
    private String password;
    @NotBlank(message = "Re-enter your password")
    private String confirmPassword;


    public UserRegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
