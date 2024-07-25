package com.example.AutoExpress.services;

import com.example.AutoExpress.dto.UserLoginDTO;
import com.example.AutoExpress.dto.UserRegisterDTO;
import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


        UserEntity mapped = modelMapper.map(data, UserEntity.class);

        userRepository.save(mapped);

        return true;
    }


    public boolean login(UserLoginDTO data) {

        Optional<UserEntity> optUser = userRepository.findByUsername(data.getUsername());

        if (optUser.isEmpty() || !passwordEncoder.matches(data.getPassword(), optUser.get().getPassword())) {
            return false;
        }

        UserEntity userEntity = optUser.get();
        //userSession.login(user);

        return true;
    }




}
