package com.Elite.Erum_Makeover.Controller;
import com.Elite.Erum_Makeover.DTO.ProfileRequest;
import com.Elite.Erum_Makeover.Model.Image;
import com.Elite.Erum_Makeover.Model.Profile;
import com.Elite.Erum_Makeover.Repository.ImageRepository;
import com.Elite.Erum_Makeover.Repository.ProfileRepository;
import com.Elite.Erum_Makeover.SecurityConfig.JwtUtil;
import com.Elite.Erum_Makeover.Services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProfileController {
    private final ProfileService profileService;
    private  final JwtUtil JwtUtil;
    // 🔥 Create or Update Profile
    @PostMapping
    public ResponseEntity<?> saveProfile(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Profile request
    ) {

        String token = authHeader.substring(7);
        String userId = JwtUtil.extractUserId(token);
        Profile savedProfile =
                profileService.saveOrUpdateProfile(userId, request);

        return ResponseEntity.ok(savedProfile);
    }

    // 🔥 Get Profile
    @GetMapping
    public ResponseEntity<?> getProfile(
            @RequestHeader("Authorization") String authHeader
    ) {

        String token = authHeader.substring(7);
        String userId = JwtUtil.extractUserId(token);

        Profile profile =
                profileService.getProfileByUserId(userId);

        return ResponseEntity.ok(profile);
    }

    // 🔥 Admin: Get Profile By UserId (No Authentication)
@GetMapping("/admin/{userId}")
public ResponseEntity<?> getProfileForAdmin(@PathVariable String userId) {

    Profile profile = profileService.getProfileByUserIdforImg(userId);

    return ResponseEntity.ok(profile);
}
    }
