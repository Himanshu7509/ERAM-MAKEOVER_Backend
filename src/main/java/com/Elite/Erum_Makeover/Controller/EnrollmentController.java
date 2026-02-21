package com.Elite.Erum_Makeover.Controller;


import com.Elite.Erum_Makeover.Model.Enrollment;
import com.Elite.Erum_Makeover.SecurityConfig.JwtUtil;
import com.Elite.Erum_Makeover.Services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService service;
  private final JwtUtil jwtUtil;
    @PostMapping("/{courseId}")
    public Enrollment enroll(
            @PathVariable String courseId,
            @RequestBody Map<String,String> req,
            @RequestHeader("Authorization") String token
    ){
        String jwt = token.substring(7);

        String userId   = jwtUtil.extractUserId(jwt);
        String fullName = jwtUtil.extractFullName(jwt);
        String email    = jwtUtil.extractEmail(jwt);

        return service.enroll(
                userId,
                fullName,
                email,
                courseId,
                req.get("phone"),
                req.get("message")
        );
    }
    @GetMapping("/admin")

    public List<Enrollment> all(){
        return service.all();
    }
}
