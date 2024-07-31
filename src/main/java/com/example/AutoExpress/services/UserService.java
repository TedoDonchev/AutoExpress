package com.example.AutoExpress.services;

import com.example.AutoExpress.dto.UserLoginDTO;
import com.example.AutoExpress.dto.UserProfileDTO;
import com.example.AutoExpress.dto.UserRegisterDTO;
import com.example.AutoExpress.entities.Role;
import com.example.AutoExpress.entities.RoleEnum;
import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.repositories.RoleRepository;
import com.example.AutoExpress.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;


    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.authService = authService;
    }

    public void register(UserRegisterDTO data) {

        UserEntity user = this.modelMapper.map(data, UserEntity.class);

        Role role = roleRepository.findByName(RoleEnum.MEMBER);
        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(data.getPassword()));

        userRepository.save(user);

    }


    public UserProfileDTO getProfileData() {
        return modelMapper.map(authService.getUser(), UserProfileDTO.class);
    }

    public boolean isUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }

    public UserEntity getUserByUserName(String userName) {
        return userRepository.findByUsername(userName).get();
    }


}
