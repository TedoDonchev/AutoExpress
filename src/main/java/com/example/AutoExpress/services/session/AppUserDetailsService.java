package com.example.AutoExpress.services.session;

import com.example.AutoExpress.entities.User;
import com.example.AutoExpress.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(this::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found!", username)));
    }

    private UserDetails mapToUserDetails(User user) {

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(List.of())
                .build();

    }


}
