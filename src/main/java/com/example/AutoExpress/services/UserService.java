package com.example.AutoExpress.services;

import com.example.AutoExpress.dto.UserLoginDTO;
import com.example.AutoExpress.dto.UserProfileDTO;
import com.example.AutoExpress.dto.UserRegisterDTO;
import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;


    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authService = authService;
    }

    public void register(UserRegisterDTO data) {

//        if (!data.getPassword().equals(data.getConfirmPassword())) {
//            return false;
//        }
//
//        boolean existsByUsernameOrEmail = userRepository.existsByUsernameOrEmail(data.getUsername(), data.getEmail());
//        if (existsByUsernameOrEmail) {
//            return false;
//        }
//
//        String encoded = passwordEncoder.encode(data.getPassword());
//        data.setPassword(encoded);
//
//
//        UserEntity mapped = modelMapper.map(data, UserEntity.class);
//
//        userRepository.save(mapped);
//
//        return true;


        UserEntity user = this.modelMapper.map(data, UserEntity.class);

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




}
