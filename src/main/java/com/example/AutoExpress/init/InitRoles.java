package com.example.AutoExpress.init;

import com.example.AutoExpress.entities.Role;
import com.example.AutoExpress.entities.RoleEnum;
import com.example.AutoExpress.entities.UserEntity;
import com.example.AutoExpress.repositories.RoleRepository;
import com.example.AutoExpress.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(2)
public class InitRoles implements CommandLineRunner {

    private final RoleRepository roleRepository;


    @Autowired
    public InitRoles(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        long count = roleRepository.count();

        if (count == 0) {
            for (RoleEnum roleEnum : RoleEnum.values()) {
                Role role = new Role(roleEnum);

                roleRepository.save(role);
            }
        }

    }

}
