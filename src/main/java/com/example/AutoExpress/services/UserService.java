package com.example.AutoExpress.services;

import com.example.AutoExpress.dto.UserRegisterDTO;
import com.example.AutoExpress.entities.User;
import com.example.AutoExpress.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;



    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public boolean register(UserRegisterDTO data) {

        if (!data.getPassword().equals(data.getConfirmPassword())) {
            return false;
        }

        boolean existsByUsernameOrEmail = userRepository.existsByUsernameOrEmail(data.getUsername(), data.getEmail());
        if (existsByUsernameOrEmail) {
            return false;
        }

        String encoded = passwordEncoder.encode(data.getPassword());
        data.setPassword(encoded);

        User mapped = modelMapper.map(data, User.class);

        userRepository.save(mapped);

        return true;
    }




}
