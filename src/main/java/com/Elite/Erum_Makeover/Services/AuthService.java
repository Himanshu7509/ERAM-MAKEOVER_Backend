package com.Elite.Erum_Makeover.Services;


import com.Elite.Erum_Makeover.DTO.AuthResponse;
import com.Elite.Erum_Makeover.DTO.LoginRequest;
import com.Elite.Erum_Makeover.DTO.RegisterRequest;
import com.Elite.Erum_Makeover.Exeptions.EmailAlreadyExistsException;
import com.Elite.Erum_Makeover.Model.Admin;
import com.Elite.Erum_Makeover.Model.Role;
import com.Elite.Erum_Makeover.Model.User;
import com.Elite.Erum_Makeover.Repository.AdminRepository;
import com.Elite.Erum_Makeover.Repository.UserRepository;
import com.Elite.Erum_Makeover.SecurityConfig.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final AdminRepository adminRepo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwt;

    // PUBLIC SIGNUP → USER ONLY
    public AuthResponse registerUser(RegisterRequest req){

        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .build();

        userRepo.save(user);

        String token = jwt.generateToken(
                user.getEmail(),
                user.getFullName(),
                user.getId(),
                "USER"
        );

        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(Role.USER)
                .build();
    }

    // LOGIN → CHECK ADMIN FIRST THEN USER
    public AuthResponse login(LoginRequest req){

        // check admin
        var adminOpt = adminRepo.findByEmail(req.getEmail());
        if(adminOpt.isPresent()){
            Admin admin = adminOpt.get();

            if(!encoder.matches(req.getPassword(), admin.getPassword()))
                throw new RuntimeException("Invalid password");

            String token = jwt.generateToken(
                    admin.getEmail(),
                    admin.getFullName(),
                    admin.getId(),
                    "ADMIN"
            );

            return AuthResponse.builder()
                    .token(token)
                    .email(admin.getEmail())
                    .role(Role.ADMIN)
                    .build();
        }

        // check user
        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid password");

        String token = jwt.generateToken(
                user.getEmail(),
                user.getFullName(),
                user.getId(),
                "USER"
        );
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(Role.USER)
                .build();
    }
}