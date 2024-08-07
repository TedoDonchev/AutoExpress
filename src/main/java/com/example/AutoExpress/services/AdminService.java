package com.example.AutoExpress.services;

import com.example.AutoExpress.entities.Role;
import com.example.AutoExpress.entities.RoleEnum;
import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final UserService userService;

    public AdminService(UserRepository userRepository, AuthService authService, UserService userService) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.userService = userService;
    }

    public void disableUser(UserEntity user) {
        changeActiveStatus(user, false);
    }

    public void enableUser(UserEntity user) {
        changeActiveStatus(user, true);
    }

    public void changeActiveStatus(UserEntity user, boolean active){
        if (authService.isAdmin() && !userService.hasAdminRole(user)) {
            user.setActive(active);
            userRepository.save(user);
        }
    }

    public UserEntity changeUsername(long id) {
        UserEntity user = userService.getById(id);



    }




}
