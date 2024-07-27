//package com.example.AutoExpress.security;
//
//import com.example.AutoExpress.entities.Role;
//import com.example.AutoExpress.entities.UserEntity;
//import com.example.AutoExpress.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository
//                            .findByUsername(username)
//                            .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found!", username)));
//
//
//        return new User(user.getUsername(), user.getPassword(), mapToGrantedAuthorities(user.getRoles()));
//
//    }
//
//    private Collection<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
//                .collect(Collectors.toList());
//    }
//}
