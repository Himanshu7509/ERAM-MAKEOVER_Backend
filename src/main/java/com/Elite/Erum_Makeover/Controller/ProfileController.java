package com.Elite.Erum_Makeover.Controller;
import com.Elite.Erum_Makeover.Model.Profile;
import com.Elite.Erum_Makeover.Repository.ProfileRepository;
import com.Elite.Erum_Makeover.SecurityConfig.JwtUtil;
import com.Elite.Erum_Makeover.Services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileRepository profileRepository;
    private  final JwtUtil JwtUtil;

    // 🔥 Create or Update Profile
    @PostMapping
    public ResponseEntity<?> saveProfile(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Profile request
    )
    {
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
    )
    {
        String token = authHeader.substring(7);
        String userId = JwtUtil.extractUserId(token);
        Profile profile =
        profileService.getProfileByUserId(userId);
        return ResponseEntity.ok(profile);
    }

    // ✅ Get All Profiles
    @GetMapping("/admin")
    public ResponseEntity<List<Profile>> getAllProfiles()
    {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    // ✅ Get One Profile by profileId
    @GetMapping("/admin/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable String profileId)
    {
        return ResponseEntity.ok(profileService.getProfileById(profileId));
    }
}
