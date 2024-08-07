package com.example.AutoExpress.dto;

import jakarta.validation.constraints.Size;

public class UserNameDTO {

    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
