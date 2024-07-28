package com.example.AutoExpress.init;

import com.example.AutoExpress.entities.Role;
import com.example.AutoExpress.entities.RoleEnum;
import com.example.AutoExpress.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InitRoles implements CommandLineRunner {

    private final RoleRepository roleRepository;

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
