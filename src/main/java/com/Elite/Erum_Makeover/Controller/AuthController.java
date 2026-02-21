package com.Elite.Erum_Makeover.Controller;

import com.Elite.Erum_Makeover.DTO.AuthResponse;
import com.Elite.Erum_Makeover.DTO.LoginRequest;
import com.Elite.Erum_Makeover.DTO.RegisterRequest;
import com.Elite.Erum_Makeover.Model.User;
import com.Elite.Erum_Makeover.Repository.UserRepository;
import com.Elite.Erum_Makeover.SecurityConfig.JwtUtil;
import com.Elite.Erum_Makeover.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest req){

        return ResponseEntity.ok(service.registerUser(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest req){

        return ResponseEntity.ok(service.login(req));
    }
}