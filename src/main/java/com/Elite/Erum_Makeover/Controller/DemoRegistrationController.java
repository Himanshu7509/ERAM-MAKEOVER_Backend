package com.Elite.Erum_Makeover.Controller;

import com.Elite.Erum_Makeover.Model.DemoRegistration;
import com.Elite.Erum_Makeover.SecurityConfig.JwtUtil;
import com.Elite.Erum_Makeover.Services.DemoRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DemoRegistrationController {

    private final DemoRegistrationService service;
    private  final JwtUtil jwtUtil;
    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('USER')")
    public DemoRegistration register(
            @RequestBody DemoRegistration demo,
            Authentication auth,
            @RequestHeader("Authorization") String authHeader
    ) {

        // Remove "Bearer "
        String token = authHeader.substring(7);

        // Extract details from JWT
        String email = jwtUtil.extractEmail(token);
        String userId = jwtUtil.extractUserId(token);

        // Set into entity
        demo.setEmail(email);
        demo.setUserId(userId);

        return service.register(demo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DemoRegistration> all() {
        return service.getAll();
    }
}