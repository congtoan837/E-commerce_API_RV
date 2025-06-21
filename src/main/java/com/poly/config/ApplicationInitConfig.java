package com.poly.config;

import java.util.*;

import com.poly.ex.UserDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.poly.entity.Role;
import com.poly.entity.User;
import com.poly.ex.content.ERole;
import com.poly.repositories.RoleRepository;
import com.poly.repositories.UserRepository;

@Configuration
public class ApplicationInitConfig {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
//            Set<User> userSet = UserDataGenerator.createSampleUsers();
//            userRepository.saveAll(userSet);

            if (userRepository.existsByUsername("admin")) return;
            for (ERole eRole : ERole.values()) {
                Role role = Role.builder()
                        .name(eRole.name())
                        .permissions(eRole.getPermissions())
                        .build();
                roleRepository.save(role);
            }

            List<Role> roles = roleRepository.findAllById(Arrays.asList(ERole.ADMIN.name()));
            User user = User.builder()
                    .username("admin")
                    .name("admin")
                    .email("")
                    .password(passwordEncoder.encode("admin"))
                    .roles(new HashSet<>(roles))
                    .build();
            userRepository.save(user);
        };
    }
}
