package com.example.springboot.Data;

import com.example.springboot.Core.Models.Role;
import com.example.springboot.Core.Models.User;
import com.example.springboot.Data.Repositories.IRoleRepository;
import com.example.springboot.Data.Repositories.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {

    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;

    public Seeder(IRoleRepository roleRepository, IUserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedRoles();
        seedUsers();
    }

    private void seedRoles() {
        if (roleRepository.findByName("USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }

        if (roleRepository.findByName("ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }
    }
    private void seedUsers() {
        if (userRepository.findByLogin("admin") == null) {
            User adminUser = new User();
            adminUser.setLogin("admin");
            adminUser.setPassword(new BCryptPasswordEncoder().encode("admin"));
            adminUser.getRoles().add(roleRepository.findByName("ADMIN").get());
            userRepository.save(adminUser);
        }

        if (userRepository.findByLogin("user") == null) {
            User normalUser = new User();
            normalUser.setLogin("user");
            normalUser.setPassword(new BCryptPasswordEncoder().encode("user"));
            normalUser.getRoles().add(roleRepository.findByName("USER").get());
            userRepository.save(normalUser);
        }
    }
}
