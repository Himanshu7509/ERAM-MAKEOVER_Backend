package com.Elite.Erum_Makeover.Controller;

import com.Elite.Erum_Makeover.Model.DemoRegistration;
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

    // ✅ USER MUST LOGIN TO REGISTER
    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('USER')")
    public DemoRegistration register(
            @RequestBody DemoRegistration demo,
            Authentication auth
    ) {
        // get logged in email from token
        String email = auth.getName();

        demo.setEmail(email); // override with logged-in email
        return service.register(demo);
    }

    // ✅ ADMIN CAN VIEW ALL REGISTRATIONS
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DemoRegistration> all() {
        return service.getAll();
    }
}