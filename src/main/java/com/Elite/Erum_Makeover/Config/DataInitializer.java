package com.Elite.Erum_Makeover.Config;

import com.Elite.Erum_Makeover.Model.Admin;
import com.Elite.Erum_Makeover.Model.User;
import com.Elite.Erum_Makeover.Repository.AdminRepository;
import com.Elite.Erum_Makeover.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        if(repo.findByEmail("admin@academy.com").isEmpty()){
            Admin admin = new Admin();
            admin.setFullName("Admin");
            admin.setEmail("admin@academy.com");
            admin.setPassword(encoder.encode("admin123"));

            repo.save(admin);
            System.out.println("✅ Admin created");
        }
    }
}